package com.evilsay.ship.Controller;

import com.evilsay.ship.DataObject.ProductCategory;
import com.evilsay.ship.DataObject.ProductInfo;
import com.evilsay.ship.Exception.ShipException;
import com.evilsay.ship.From.ProductInfoFrom;
import com.evilsay.ship.Service.PictureBedService;
import com.evilsay.ship.Service.ProductCategoryService;
import com.evilsay.ship.Service.ProductInfoService;
import com.evilsay.ship.Utils.KeyUtil;
import com.evilsay.ship.Utils.SellerUtils.ShopInfoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**卖家端商品列表
 * @Author: EvilSay
 * @Date: 2019/2/3 15:10
 */
@Controller
@RequestMapping("seller/product")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductInfoService service;

    @Autowired
    private ShopInfoUtils shopInfoUtils;

    @Autowired
    private ProductCategoryService categoryService;

    @Autowired
    private PictureBedService PictureBedService;
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue ="1")Integer page,
                             @RequestParam(value = "size",defaultValue = "10")Integer size,
                             Map<String,Object> map){
        PageRequest request = new PageRequest(page - 1, size);
        Page<ProductInfo> productInfo = service.findByShopType(request,shopInfoUtils.getShopType());
        map.putAll(shopInfoUtils.shopInfoUtils(map));
        map.put("productInfo",productInfo);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("product/list",map);
    }
    @GetMapping("on_sale")
    public ModelAndView 上架(@RequestParam("productId") String productId,
                            Map<String,Object> map){
        try {
            service.on_save(productId, shopInfoUtils.getShopType());
        }catch (ShipException e){
            log.error("[卖家下架订单]商品状态异常");
            map.put("msg",e.getMessage());
            map.put("url","/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/seller/product/list");
        return new ModelAndView("common/success",map);
    }
    @GetMapping("off_sale")
    public ModelAndView 下架(@RequestParam("productId") String productId,
                           Map<String,Object> map){
        try {
            service.off_save(productId, shopInfoUtils.getShopType());
        }catch (ShipException e){
            log.error("[卖家下架订单]商品状态异常");
            map.put("msg",e.getMessage());
            map.put("url","/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("url","/seller/product/list");
        return new ModelAndView("common/success",map);
    }
    //(新增)修改
    @GetMapping("index")
    public ModelAndView index(@RequestParam(value = "productId",required = false)String productId,
                              Map<String,Object> map){

        if (!StringUtils.isEmpty(productId)){
            ProductInfo productInfo = service.findShopTypeANDProduct(shopInfoUtils.getShopType(), productId);
            map.put("productInfo",productInfo);
            map.putAll(shopInfoUtils.shopInfoUtils(map));
        }
        List<ProductCategory> productCategoryList = categoryService.findByShopType(shopInfoUtils.getShopType());
        map.put("productCategory",productCategoryList);
        map.putAll(shopInfoUtils.shopInfoUtils(map));
        return new ModelAndView("product/index",map);
    }
    //(新增)添加

    /**
     * 上传图片需要一个正常的域名，否则拿官方测试域名(有过期时间)
     * @param productInfoFrom
     * @param file
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("save")
    public ModelAndView save(@Valid ProductInfoFrom productInfoFrom,
                             @RequestParam(value = "file",required = false) MultipartFile file,
                             BindingResult bindingResult,
                             Map<String,Object>map){
        if (bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/seller/product/index");
            return new ModelAndView("common/error",map);
        }
        ProductInfo productInfo = new ProductInfo();
        try {
            if (!StringUtils.isEmpty(productInfoFrom.getProductId())){
                productInfo = service.findOne(productInfoFrom.getProductId());
            }else {
                productInfoFrom.setProductId(KeyUtil.genUniqueKey());
            }
            String productIco = PictureBedService.uploadImageToQiniuyun(file);

            BeanUtils.copyProperties(productInfoFrom,productInfo);

            productInfo.setShopType(shopInfoUtils.getShopType());

            productInfo.setProductIcon(productIco);

            if (productInfoFrom.getProductDiscount() == null){
                service.save(productInfo);
            }else{
                productInfo.setProductDiscount(productInfoFrom.getProductDiscount().divide(new BigDecimal(10)));
                service.save(productInfo);
            }
        }catch (ShipException e){
            map.put("url","/seller/product/index");
            map.put("msg",e.getMessage());
            return new ModelAndView("common/error",map);
        } catch (Exception e) {
            map.put("msg","上传图片错误");
            map.put("url","/seller/product/index");
            return new ModelAndView("common/error");
        }
        map.put("url","/seller/product/list");
        map.putAll(shopInfoUtils.shopInfoUtils(map));
        return new ModelAndView("common/success",map);
    }
}

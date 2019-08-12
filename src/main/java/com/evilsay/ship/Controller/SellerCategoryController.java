package com.evilsay.ship.Controller;

import com.evilsay.ship.DataObject.ProductCategory;
import com.evilsay.ship.DataObject.ShopInfo;
import com.evilsay.ship.Exception.ShipException;
import com.evilsay.ship.From.ProductCategoryFrom;
import com.evilsay.ship.Service.ProductCategoryService;
import com.evilsay.ship.Service.ShopInfoService;
import com.evilsay.ship.Utils.SellerUtils.ShopInfoUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**店铺类目
 * @Author: EvilSay
 * @Date: 2019/2/4 23:32
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {
    @Autowired
    private ProductCategoryService categoryService;
    @Autowired
    private ShopInfoUtils shopInfoUtils;
    @GetMapping("list")
    public ModelAndView list(Map<String,Object> map){
        List<ProductCategory> productCategoryList = categoryService.findByShopType(shopInfoUtils.getShopType());
        map.put("categoryList",productCategoryList);
        map.putAll(shopInfoUtils.shopInfoUtils(map));
        return new ModelAndView("category/list",map);
    }
    @GetMapping("index")
    public ModelAndView index(@RequestParam(value = "categoryId",required = false)Integer categoryId,
                              Map<String,Object> map) {
        if (categoryId != null){
            ProductCategory productCategory = categoryService.finOne(categoryId);
            map.put("productCategory",productCategory);
            map.putAll(shopInfoUtils.shopInfoUtils(map));
        }
        map.putAll(shopInfoUtils.shopInfoUtils(map));
        return new ModelAndView("category/index",map);
    }
    @PostMapping("save")
    public ModelAndView save(@Valid ProductCategoryFrom productCategoryFrom,
                             BindingResult bindingResult,
                             Map<String,Object> map){
        if (bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/seller/category/index");
            return new ModelAndView("common/error",map);
        }
        ProductCategory productCategory = new ProductCategory();
        try {

            if (productCategoryFrom.getCategoryId() != null ){
                productCategory = categoryService.finOne(productCategoryFrom.getCategoryId());
                BeanUtils.copyProperties(productCategoryFrom,productCategory);
                productCategory.setShopType(shopInfoUtils.getShopType());
                categoryService.update(productCategory);
            }else{
                BeanUtils.copyProperties(productCategoryFrom,productCategory);
                productCategory.setShopType(shopInfoUtils.getShopType());
                categoryService.save(productCategory);
            }
        }catch (ShipException e){
            map.put("url","/seller/category/index");
            map.put("msg",e.getMessage());
            return new ModelAndView("common/error",map);
        }
        map.put("url","/seller/category/list");
        return new ModelAndView("common/success",map);

    }
}

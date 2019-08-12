package com.evilsay.ship.Controller.Buyer;

import com.evilsay.ship.Annotation.NoRepeatSubmit;
import com.evilsay.ship.DataObject.ProductCategory;
import com.evilsay.ship.DataObject.ProductInfo;
import com.evilsay.ship.Enums.ResultShopEnum;
import com.evilsay.ship.Exception.ShipException;
import com.evilsay.ship.Service.ProductCategoryService;
import com.evilsay.ship.Service.ProductInfoService;

import com.evilsay.ship.Utils.ResultVOUtil;
import com.evilsay.ship.VO.ProductInfoVO.ProductInfoVO;
import com.evilsay.ship.VO.ProductInfoVO.ProductVO;
import com.evilsay.ship.VO.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**商品API
 * @Author: EvilSay
 * @Date: 2019/1/21 23:26
 *
 */
@RestController
@RequestMapping("/buyer/product")
@Api(tags = "买家商品列表API")
public class BuyerProductController {
    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;


    /**
     * 根据前端传来的shopType返回Json
     * @param shopType 前端传来的参数
     * @return
     */
    @GetMapping("list")
    @ApiOperation(value = "返回指定店铺的商品列表")
    @NoRepeatSubmit
    public ResultVO List(@RequestParam(value = "shoptype",required = false) Integer shopType){

        if (shopType == null){

            throw new ShipException(ResultShopEnum.SHOP_INFO_NOT_EXITS.getCode(),ResultShopEnum.SHOP_INFO_NOT_EXITS.getMessage());
        }

        //创建一次性类目查询
        List<Integer> productCategoryList = new ArrayList<>();
        //查询归属商家上架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll(shopType);


        for (ProductInfo productInfo : productInfoList){
            productCategoryList.add(productInfo.getCategoryType());
        }
        //根据类目中的type查询商品集合
        List<ProductCategory> categoryTypeIn = productCategoryService.findByCategoryTypeIn(productCategoryList, shopType);
        //拼接数据类目
        List<ProductVO> productVOList = new ArrayList<>();

        for (ProductCategory productCategory : categoryTypeIn){

            if (productCategory.getShopType().equals(shopType)){
                ProductVO productVO = new ProductVO();
                productVO.setCategoryName(productCategory.getCategoryName());
                productVO.setCategoryType(productCategory.getCategoryType());

                List<ProductInfoVO> productInfoVOList = new ArrayList<>();

                for (ProductInfo productInfo : productInfoList){

                    if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                        ProductInfoVO productInfoVO = new ProductInfoVO();
                        BeanUtils.copyProperties(productInfo,productInfoVO);
                        productInfoVOList.add(productInfoVO);
                    }
                }
                productVO.setProductInfoVOList(productInfoVOList);
                productVOList.add(productVO);
            }
        }
        return ResultVOUtil.success(productVOList);
    }
}

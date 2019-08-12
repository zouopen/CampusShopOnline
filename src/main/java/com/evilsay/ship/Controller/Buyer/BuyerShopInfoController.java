package com.evilsay.ship.Controller.Buyer;


import com.evilsay.ship.DataObject.ShopCategory;
import com.evilsay.ship.DataObject.ShopInfo;
import com.evilsay.ship.Enums.ResultEnum;
import com.evilsay.ship.Exception.ShipException;
import com.evilsay.ship.Service.ShopCategoryService;
import com.evilsay.ship.Service.ShopInfoService;
import com.evilsay.ship.Utils.ResultVOUtil;
import com.evilsay.ship.VO.ResultVO;
import com.evilsay.ship.VO.ShopVO.ShopInfoVO;
import com.evilsay.ship.VO.ShopVO.ShopVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**店铺API与店铺归属类目
 * @Author: EvilSay
 * @Date: 2019/1/22 2:39
 */
@RestController
@RequestMapping("/buyer/shop")
@Api(tags = "店铺信息API")
public class BuyerShopInfoController {
    @Autowired
    private ShopInfoService shopInfoService;
    @Autowired
    private ShopCategoryService categoryService;
    @ApiOperation(value = "店铺所有信息")
    @GetMapping("list")
    public ResultVO list(){
        //查询所有在营业时间的店铺
        List<ShopInfo> shopInfoList = shopInfoService.findUpAll();
        //查询类目(一次性查询)
        List<Integer>  categoryList = new ArrayList<>();
        //在线店铺的类目
        for(ShopInfo shopInfo : shopInfoList){
            categoryList.add(shopInfo.getShopCategoryType());
        }
        //根据店铺类目中的type查询店铺集合
        List<ShopCategory> shopCategoryList = categoryService.findByShopCategoryTypeList(categoryList);
        //添加数据集
        List<ShopVO> shopVOList = new ArrayList<>();
        //店铺数据拼接
        for (ShopCategory category : shopCategoryList){
            //类目
            ShopVO shopVO = new ShopVO();
            shopVO.setShopCategoryName(category.getShopCategoryName());
            shopVO.setShopCategoryType(category.getShopCategoryType());
            //店铺
            List<ShopInfoVO> shopInfoVOList = new ArrayList<>();
            for (ShopInfo shopInfo : shopInfoList){
                if (shopInfo.getShopCategoryType().equals(category.getShopCategoryType())){
                    ShopInfoVO shopInfoVO = new ShopInfoVO();
                    BeanUtils.copyProperties(shopInfo,shopInfoVO);
                    shopInfoVOList.add(shopInfoVO);
                }
            }
            //添加数据集
            shopVO.setShopInfoVOList(shopInfoVOList);
            shopVOList.add(shopVO);
        }

        return ResultVOUtil.success(shopVOList);
    }
    @GetMapping("listcategory")
    public ResultVO listCategory(@RequestParam("shopcategorytype") Integer shopCategoryType,
                                 @RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "size", defaultValue = "10") Integer size) {
        PageRequest pageRequest = new PageRequest(page - 1, size);

        ShopCategory shopCategory = categoryService.findByShopCategoryType(shopCategoryType);

        if (shopCategory == null){
            throw new ShipException(ResultEnum.SHOP_CATEGORY_NOT_EXITS.getCode(),ResultEnum.SHOP_CATEGORY_NOT_EXITS.getMessage());
        }

        Page<ShopInfo> categoryType = shopInfoService.findCategoryType(shopCategoryType, pageRequest);

        if(categoryType.getContent().size() == 0){
            throw new ShipException(ResultEnum.SHOP_CATEGORY_NOT_EXITS.getCode(),ResultEnum.SHOP_CATEGORY_NOT_EXITS.getMessage());
        }

        List<ShopVO> shopVOList = new ArrayList<>();

        ShopVO shopVO = new ShopVO();

        shopVO.setShopCategoryName(shopCategory.getShopCategoryName());

        shopVO.setShopCategoryType(shopCategory.getShopCategoryType());

        List<ShopInfoVO> shopInfoVOList = new ArrayList<>();

        for (ShopInfo shopInfo : categoryType.getContent()) {

            ShopInfoVO shopInfoVO = new ShopInfoVO();

            BeanUtils.copyProperties(shopInfo, shopInfoVO);

            shopInfoVOList.add(shopInfoVO);
        }
        shopVO.setShopInfoVOList(shopInfoVOList);
        shopVOList.add(shopVO);
        return ResultVOUtil.success(shopVOList);
    }
}

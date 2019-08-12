package com.evilsay.ship.Controller.Buyer;

import com.evilsay.ship.Annotation.NoRepeatSubmit;
import com.evilsay.ship.DataObject.ProductInfo;
import com.evilsay.ship.DataObject.ShopInfo;
import com.evilsay.ship.Enums.ResultEnum;
import com.evilsay.ship.Exception.ShipException;
import com.evilsay.ship.Service.ProductInfoService;
import com.evilsay.ship.Service.ShopInfoService;
import com.evilsay.ship.Utils.ResultVOUtil;
import com.evilsay.ship.VO.ProductInfoVO.ProductInfoVO;
import com.evilsay.ship.VO.ResultVO;
import com.evilsay.ship.VO.Search.SearchVO;
import com.evilsay.ship.VO.ShopVO.ShopInfoVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 2  @Author: Hk
 * 3  @Date: 2019/3/28 23:55
 * 4
 */

@RestController
@RequestMapping("/buyer/search")
@Api(tags = "搜索结果API")
@Slf4j
public class SearchController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ShopInfoService shopInfoService;

    @PostMapping("/result")
    @NoRepeatSubmit
    public ResultVO searchResult(@RequestParam(value = "name",required = false) String name,
                                 @RequestParam(value = "page",defaultValue = "1")Integer page,
                                 @RequestParam(value = "size",defaultValue = "5")Integer size){

        if (name == null || name.equals("")){
            throw new ShipException(ResultEnum.SEARCH_ERROR.getCode(),ResultEnum.SEARCH_ERROR.getMessage());
        }

        PageRequest pageRequest = new PageRequest(page-1,size);

        Page<ProductInfo> productInfoList = productInfoService.findByProductNameLike(name,pageRequest);

        Page<ShopInfo> shopInfoList = shopInfoService.findByShopNameLike(name,pageRequest);

        List<SearchVO> searchVOList = new ArrayList<>();

        SearchVO  productInfoSearchVO = new SearchVO();

        SearchVO  shopInfoSearchVo = new SearchVO();

        productInfoSearchVO.setCategoryName("商品");

        shopInfoSearchVo.setCategoryName("店铺");

        List<ProductInfoVO> productInfoVOList = new ArrayList<>();

        List<ShopInfoVO> shopInfoVOList = new ArrayList<>();
        for (ProductInfo productInfo : productInfoList.getContent()){
            ProductInfoVO productInfoVO = new ProductInfoVO();
            BeanUtils.copyProperties(productInfo,productInfoVO);
            productInfoVOList.add(productInfoVO);
        }
        for (ShopInfo shopInfo : shopInfoList.getContent()){
            ShopInfoVO shopInfoVO = new ShopInfoVO();
            BeanUtils.copyProperties(shopInfo,shopInfoVO);
            shopInfoVOList.add(shopInfoVO);
        }
        productInfoSearchVO.setProductInfoVOList(productInfoVOList);
        shopInfoSearchVo.setShopInfoVOList(shopInfoVOList);
        searchVOList.add(productInfoSearchVO);
        searchVOList.add(shopInfoSearchVo);
        return ResultVOUtil.success(searchVOList);
    }
}

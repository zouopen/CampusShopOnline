package com.evilsay.ship.VO.Search;

import com.evilsay.ship.VO.ProductInfoVO.ProductInfoVO;
import com.evilsay.ship.VO.ShopVO.ShopInfoVO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: EvilSay
 * @Date: 2019/3/28 10:44
 */
@Data
public class SearchVO {

    @JsonProperty("categoryname")
    private String categoryName;

    @JsonProperty("productinfo")
    private List<ProductInfoVO> productInfoVOList;

    @JsonProperty("shopinfo")
    private List<ShopInfoVO> shopInfoVOList;

}

package com.evilsay.ship.VO.ProductInfoVO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/** 商品详情
 * @Author: EvilSay
 * @Date: 2019/1/21 23:41
 */
@Data
public class ProductInfoVO implements Serializable {

    private static final long serialVersionUID = -1297361294974196870L;

    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("shoptype")
    private Integer shopType;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("discount")
    private BigDecimal productDiscount;

    @JsonProperty("icon")
    private String productIcon;
}

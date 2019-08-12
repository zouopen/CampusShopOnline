package com.evilsay.ship.DTO;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/** 购物车
 * @Author: EvilSay
 * @Date: 2019/1/23 22:38
 */
@Data
public class CartDTO {
    //商品ID
    @Size(max = 19)
    private String productId;
    //商品数量
    @Max(2)
    private Integer productQuantity;
    //店铺编号
    @Min(1)
    private Integer shopType;

    public CartDTO(String productId, Integer productQuantity, Integer shopType) {
        this.productId = productId;
        this.productQuantity = productQuantity;
        this.shopType = shopType;
    }
}

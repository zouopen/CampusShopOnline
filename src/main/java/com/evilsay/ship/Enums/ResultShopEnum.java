package com.evilsay.ship.Enums;

import lombok.Getter;

/**
 * @Author: EvilSay
 * @Date: 2019/1/23 22:31
 */
@Getter
public enum  ResultShopEnum {
    SHOP_INFO_NOT_EXITS(100,"店铺信息不存在,请输入店铺信息"),
    SHOP_NOT_EXITS(101,"店铺不存在或店铺暂没有订单"),
    SHOP_ORDER_ERROR(102,"店铺订单异常"),
    SHOP_ORDER_CANCEL_ERROR(103,"店铺取消订单异常"),
    OPENID_ERROR(104,"openId异常你无权查看该店铺订单详情"),
    CATEGORY_ERROR(105,"添加类目异常<重复!>")
    ;

    ResultShopEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String  message;
}

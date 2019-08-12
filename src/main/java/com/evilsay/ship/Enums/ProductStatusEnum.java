package com.evilsay.ship.Enums;

import lombok.Getter;

/** 商品状态
 * @Author: EvilSay
 * @Date: 2019/1/21 0:34
 */
@Getter
public enum ProductStatusEnum implements CodeEnum {
    UP(0,"上架"),
    DOWN(1,"下架"),
    OUT_OF_STOCK(2,"无货"),
    CATEGORY_ERROR(3,"添加类目异常"),
    ;
    private Integer code;
    private String  message;
    ProductStatusEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}

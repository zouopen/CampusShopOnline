package com.evilsay.ship.Enums;

import lombok.Getter;

/**
 * @Author: EvilSay
 * @Date: 2019/1/21 1:03
 */
@Getter
public enum ShopStatusEnum implements CodeEnum {
    ONLINE(0,"营业时间"),
    OFFLINE(1,"非营业时间"),
    ERROR_CLOSE_SHOP(2,"打烊异常"),
    ;
    private Integer code;
    private String  message;

    ShopStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

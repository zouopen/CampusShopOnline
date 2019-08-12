package com.evilsay.ship.Enums;

import lombok.Getter;

/**
 * @Author: EvilSay
 * @Date: 2019/1/23 21:24
 */
@Getter
public enum  PayStatusEnum implements CodeEnum {
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功")
    ;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String  message;
}

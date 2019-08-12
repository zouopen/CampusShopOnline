package com.evilsay.ship.Enums;

import lombok.Getter;

/**
 * @Author: EvilSay
 * @Date: 2019/1/23 21:20
 */
@Getter
public enum  OrderMasterStatusEnum implements CodeEnum {
    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"已取消")
    ;
    private Integer code;
    private String  message;

    OrderMasterStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

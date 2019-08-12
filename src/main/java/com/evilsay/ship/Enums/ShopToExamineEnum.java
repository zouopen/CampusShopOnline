package com.evilsay.ship.Enums;

import lombok.Getter;

/**
 * 2 * @Author: Hk
 * 3 * @Date: 2019/4/4 19:30
 * 4
 */
@Getter
public enum ShopToExamineEnum implements CodeEnum{

    To_BE_AUDITED(0,"等待审核"),
    ADOPT(1,"审核通过"),
    NOT_PASS(2,"审核未通过");
    private Integer code;
    private String  message;

    ShopToExamineEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

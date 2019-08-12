package com.evilsay.ship.Enums;

import lombok.Getter;

/**
 * 2 * @Author: Hk
 * 3 * @Date: 2019/4/27 1:41
 * 4
 */
@Getter
public enum ResultAddressEnum {
    ADDRESS_NUM_ERROR(1,"地址数量超过5条"),
    ADDRESS_OPENID_NULL(2,"openid不能为空")
    ;

    ResultAddressEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String  message;
}

package com.evilsay.ship.From;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * @Author: EvilSay
 * @Date: 2019/1/25 19:01
 */
@Data
public class OrderFrom {

    @NotEmpty(message = "姓名必填")
    @Size(max = 5)
    private String name;

    @NotEmpty(message = "手机号码必填")
    @Size(max = 14)
    private String phone;

    @NotEmpty(message = "地址必填")
    private String address;

    @NotEmpty(message = "缺少openID")
    private String openid;

    @NotEmpty(message = "购物车不能为空")
    private String items;

}

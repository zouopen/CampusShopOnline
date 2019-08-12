package com.evilsay.ship.From;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * 2 * @Author: Hk
 * 3 * @Date: 2019/4/26 17:30
 * 4
 */
@Data
public class BuyerAddressFrom {

    private Integer id;

    //买家微信Openid
    @NotEmpty(message = "买家微信openId不能为空")
    private String buyerOpenid;

    //买家名字
    @NotEmpty(message = "买家姓名不能为空")
    private String buyerName;

    //买家电话号码
    @NotEmpty(message = "买家电话不能为空")
    private String buyerPhone;

    //买家地址
    @NotEmpty(message = "买家地址不能为空")
    private String buyerAddress;

}

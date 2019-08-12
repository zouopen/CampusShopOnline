package com.evilsay.ship.From;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * 2 * @Author: Hk
 * 3 * @Date: 2019/4/6 15:21
 * 4
 */
@Data
public class MobileShopFrom {

    @NotBlank(message = "username不能为空")
    private String username;
    @NotBlank(message = "password不能为空")
    private String password;
    @NotBlank(message = "商店名不能为空")
    private String shopName;
    //店铺创建者
    @NotBlank(message = "创建人不能为空")
    private String shopFounder;
    //店铺描述
    private String shopDescription;
    //创建者电话号码
    @NotBlank(message = "电话号码不能为空")
    private String shopPhone;
    //微信openid
    private String openid;
    //店铺图片
    private String shopImg;
    //店铺类目编号
    private Integer shopCategoryType;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

}

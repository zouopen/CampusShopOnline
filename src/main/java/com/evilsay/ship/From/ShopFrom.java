package com.evilsay.ship.From;

import com.evilsay.ship.Enums.ShopStatusEnum;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author: EvilSay
 * @Date: 2019/2/20 23:04
 */
@Data
public class ShopFrom {

    private Integer userId;

    private Integer shopId;

    private String username;

    private String password;
    //店铺编号
    private Integer shopType;
    //店铺名称

    private String shopName;
    //店铺创建者

    private String shopFounder;
    //店铺描述

    private String shopDescription;
    //创建者电话号码

    private String shopPhone;
    //微信openid
    private String openid;
    //店铺图片
    private String shopImg;
    //商店状态,0营业时间1非营业时间
    private Integer shopStatus = ShopStatusEnum.ONLINE.getCode();
    //店铺类目编号

    private Integer shopCategoryType;

    private String createBy;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
}

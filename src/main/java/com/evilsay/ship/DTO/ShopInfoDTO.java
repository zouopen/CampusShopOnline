package com.evilsay.ship.DTO;

import com.evilsay.ship.Enums.ShopStatusEnum;
import com.evilsay.ship.Utils.EnumUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * 2 * @Author: Hk
 * 3 * @Date: 2019/4/5 20:57
 * 4
 */

@Data
public class ShopInfoDTO {

    private Integer shopId;
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

    private Integer shopStatus;
    //店铺类目编号
    private Integer shopCategoryType;
    //店铺审核
    private Integer shopToExamine;

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public ShopStatusEnum shopStatusEnum(){ return EnumUtils.getByCode(shopStatus,ShopStatusEnum.class); }
}

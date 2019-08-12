package com.evilsay.ship.VO.ShopVO;

import lombok.Data;


/**
 * @Author: EvilSay
 * @Date: 2019/3/29 17:04
 */
@Data
public class ShopUserLoginVO {

    private Integer id;
    //店铺编号
    private Integer shopType;
    //登录权限
    private String role;
    //登录名
    private String username;
    //店铺名称
    private String shopName;
    //电话号码
    private String shopPhone;
    //创建人
    private String shopFounder;
    //店铺审核状态
    private Integer shopToExamine;
}

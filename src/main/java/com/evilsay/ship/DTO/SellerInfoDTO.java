package com.evilsay.ship.DTO;

import com.evilsay.ship.Enums.LoginStatusEnums;
import com.evilsay.ship.Utils.DataUtils;
import com.evilsay.ship.Utils.EnumUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;

/**
 * @Author: EvilSay
 * @Date: 2019/2/18 22:09
 */
@Data
public class SellerInfoDTO {
    private Integer id;

    private String username;
    //店铺编号
    private String  shopName;

    private Integer status;
    //卖家微信openid
    private String openid;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    private Integer roleId;

    private String roleName;

    private String createBy;

    @JsonIgnore
    public LoginStatusEnums roleStatusEnums(){ return EnumUtils.getByCode(status,LoginStatusEnums.class); }
}

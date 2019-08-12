package com.evilsay.ship.Service.Impl;

import com.evilsay.ship.DTO.ShiroUserDTO;
import com.evilsay.ship.DataObject.SellerInfo;
import com.evilsay.ship.DataObject.SellerRole;
import com.evilsay.ship.DataObject.SellerUserRole;
import com.evilsay.ship.Enums.LoginStatusEnums;
import com.evilsay.ship.Exception.ShipException;
import com.evilsay.ship.Service.SellerInfoService;
import com.evilsay.ship.Service.SellerRoleService;
import com.evilsay.ship.Service.SellerUserRoleService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author: EvilSay
 * @Date: 2019/2/12 17:54
 */
@Service
public class ShiroImpl {
    @Autowired
    private SellerInfoService sellerInfoService;
    @Autowired
    private SellerUserRoleService sellerUserRoleService;
    @Autowired
    private SellerRoleService sellerRoleService;

    public SellerInfo user(String user){
        SellerInfo sellerInfo = sellerInfoService.findByUserName(user);
        if (sellerInfo == null){
            throw new AuthenticationException(LoginStatusEnums.LOGIN_PARAM_ERROR.getMessage());
        }
        return sellerInfo;
    }

    public ShiroUserDTO shiroUser(SellerInfo sellerInfo){
        ShiroUserDTO shiroUserDTO = new ShiroUserDTO();
        SellerUserRole userId = sellerUserRoleService.findByUserId(sellerInfo.getId());
        shiroUserDTO.setUsername(sellerInfo.getUsername());
        shiroUserDTO.setId(sellerInfo.getId());
        shiroUserDTO.setShopType(sellerInfo.getShopType());
        SellerRole sellerRole = sellerRoleService.findOne(userId.getRoleId());
        shiroUserDTO.setRole(sellerRole.getValue());
        shiroUserDTO.setCreateTime(sellerInfo.getCreateTime());
        return shiroUserDTO;
    }
    //登录密码解密
    public SimpleAuthenticationInfo buildAuthenticationInfo(ShiroUserDTO shiroUser, SellerInfo user, String realmName) {
        String credentials = user.getPassword();
        // 拿到账号盐值
        String source = user.getSalt();

        ByteSource credentialsSalt = new Md5Hash(source);

        return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, realmName);
    }
}

package com.evilsay.ship.Security;

import com.evilsay.ship.DTO.ShiroUserDTO;
import com.evilsay.ship.DataObject.SellerInfo;
import com.evilsay.ship.Service.Impl.ShiroImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: EvilSay
 * @Date: 2019/2/12 20:36
 */
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private ShiroImpl shiro;
    @Override
    //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        ShiroUserDTO user = (ShiroUserDTO) principalCollection.getPrimaryPrincipal();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        info.addRole(user.getRole());

        info.addStringPermission("/index");

        return info;
    }
    //登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        SellerInfo sellerInfo = shiro.user(token.getUsername());

        ShiroUserDTO shiroUserDTO = shiro.shiroUser(sellerInfo);

        return shiro.buildAuthenticationInfo(shiroUserDTO,sellerInfo,super.getName());
    }
    //密码校验
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //加密方式
        hashedCredentialsMatcher.setHashAlgorithmName(ShiroKit.hashAlgorithmName);
        //加密次数
        hashedCredentialsMatcher.setHashIterations(ShiroKit.hashIterations);

        super.setCredentialsMatcher(hashedCredentialsMatcher);
    }


}

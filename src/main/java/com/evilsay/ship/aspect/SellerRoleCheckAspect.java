package com.evilsay.ship.aspect;

import com.evilsay.ship.DTO.ShiroUserDTO;
import com.evilsay.ship.DataObject.SellerSystemSecurity;
import com.evilsay.ship.Enums.LoginStatusEnums;
import com.evilsay.ship.Exception.LoginException;
import com.evilsay.ship.Service.SellerSystemSecurityService;
import com.evilsay.ship.Utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * @Author: EvilSay
 * @Date: 2019/3/5 11:15
 */
@Aspect
@Component
@Slf4j
public class SellerRoleCheckAspect {

    @Autowired
    private SellerSystemSecurityService service;

    @Pointcut("execution(public * com.evilsay.ship.Controller.User*.*(..))" +
            "&& !execution(public * com.evilsay.ship.Controller.UserLoginController.*(..))")
    public void Role() {
    }
    @Pointcut("execution(public * com.evilsay.ship.Controller.Mobile.AppUserLoginController.*(..))")
    public void mobileRole() {
    }
    @After("Role()")
    public  void checkRole()  {
        //获取当前线程访问的请求属性
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取属性中的服务器请求
        HttpServletRequest request = servletRequestAttributes.getRequest();
        //获取服务器请求中的用户主体
        ShiroUserDTO shiroUserDTO = (ShiroUserDTO) request.getUserPrincipal();

        //权限值判断
            if (!shiroUserDTO.getRole().equalsIgnoreCase(LoginStatusEnums.ADMIN.getMessage())) {
                SellerSystemSecurity sellerSystemSecurity = new SellerSystemSecurity();
                sellerSystemSecurity.setSellerIp(request.getRemoteAddr());
                sellerSystemSecurity.setIpId(KeyUtil.genUniqueKey());
                sellerSystemSecurity.setFailureCause(LoginStatusEnums.LOGIN_ROLE_ERROR.getMessage());
                service.save(sellerSystemSecurity);
                // TODO: 2019/3/6 越权IP可能需要记录到数据库.
                log.error("非法越权记录已记录={}", request.getRemoteAddr());

                throw new LoginException();
        }
    }
    @After("mobileRole()")
    public  void mobileCheckRole()  {
        //获取当前线程访问的请求属性
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取属性中的服务器请求
        HttpServletRequest request = servletRequestAttributes.getRequest();
        //获取服务器请求中的用户主体
        ShiroUserDTO shiroUserDTO = (ShiroUserDTO) request.getUserPrincipal();

        //密码错误次数判断
        if (shiroUserDTO == null) {
            SellerSystemSecurity sellerSystemSecurity = new SellerSystemSecurity();
            sellerSystemSecurity.setSellerIp(request.getRemoteAddr());
            sellerSystemSecurity.setIpId(KeyUtil.genUniqueKey());
            sellerSystemSecurity.setFailureCause(LoginStatusEnums.LOGIN_BLACK_REPLACE.getMessage());
            service.save(sellerSystemSecurity);

            log.error("登录错误={}", request.getRemoteAddr());
        }
    }
}

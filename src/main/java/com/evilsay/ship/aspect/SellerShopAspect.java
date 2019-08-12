package com.evilsay.ship.aspect;

import com.evilsay.ship.DTO.ShiroUserDTO;
import com.evilsay.ship.Exception.LoginException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: EvilSay
 * @Date: 2019/3/6 9:20
 */
@Aspect
@Component
@Slf4j
public class SellerShopAspect {



    @Pointcut("execution(public * com.evilsay.ship.Controller.Seller*.*(..))")
    public void shop(){

    }
    @After("shop()")
    public void shopCheck(){
        //获取当前线程访问的请求属性
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取属性中的服务器请求
        HttpServletRequest request = servletRequestAttributes.getRequest();
        //获取服务器请求中的用户主体
        ShiroUserDTO shiroUserDTO  = (ShiroUserDTO) request.getUserPrincipal();

        if (shiroUserDTO.getShopType() == null){

            log.error("检测到非法登录已记录IP={},登录者={}", request.getRemoteAddr() , shiroUserDTO.getRole());

            throw new LoginException();
        }
    }
}

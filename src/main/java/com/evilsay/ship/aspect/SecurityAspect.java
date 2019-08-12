package com.evilsay.ship.aspect;

import com.evilsay.ship.DataObject.SellerSystemSecurity;
import com.evilsay.ship.Exception.MobileBlackUserException;
import com.evilsay.ship.Exception.PCBlackUserException;
import com.evilsay.ship.Service.SellerSystemSecurityService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/** 黑名单阻止
 * @Author: EvilSay
 * @Date: 2019/3/7 10:44
 */
@Aspect
@Component
@Slf4j
public class SecurityAspect {


    @Autowired
    private SellerSystemSecurityService service;

    @Pointcut("execution(public * com.evilsay.ship.Controller..*(..))"+
            "&& !execution(public * com.evilsay.ship.Controller.Mobile..*(..))")
    public void check(){ }

    @Pointcut("execution(public * com.evilsay.ship.Controller.Mobile..*(..))")
    public void mobileCheck(){ }

    @Before("check()")
    public void checkBlack(){
        List<SellerSystemSecurity> serviceAll = service.findAll();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        for (SellerSystemSecurity systemSecurity : serviceAll){
            Integer size = 20;
            if (request.getRemoteAddr().equalsIgnoreCase(systemSecurity.getSellerIp())
                    && size.equals(systemSecurity.getSellerRepeat())){
//                新建一个黑名单异常
                throw new PCBlackUserException();
            }
        }
    }
    @Before("mobileCheck()")
    public void mobileCheckBlack(){
        List<SellerSystemSecurity> serviceAll = service.findAll();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        for (SellerSystemSecurity systemSecurity : serviceAll){
            Integer size = 20;
            if (request.getRemoteAddr().equalsIgnoreCase(systemSecurity.getSellerIp())
                    && size.equals(systemSecurity.getSellerRepeat())){
//                新建一个黑名单异常
                throw new MobileBlackUserException();
            }
        }
    }
}

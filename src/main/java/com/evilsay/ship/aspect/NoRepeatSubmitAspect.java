package com.evilsay.ship.aspect;

import com.evilsay.ship.Annotation.NoRepeatSubmit;
import com.evilsay.ship.Enums.LoginStatusEnums;
import com.evilsay.ship.Exception.ShipException;
import com.evilsay.ship.Utils.ResultVOUtil;
import com.google.common.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: EvilSay
 * @Date: 2019/4/14 21:13
 */
@Aspect
@Component
@Slf4j
public class NoRepeatSubmitAspect {

    @Autowired
    private Cache<String,Integer> cache;

    /**
     *
     * @param pjp 获取当前被注解的方法
     * @param nrs 传入注解
     * @return
     */
    @Around("execution(* com.evilsay.ship.Controller..*(..)) && @annotation(nrs)")
    public Object arround(ProceedingJoinPoint pjp,NoRepeatSubmit nrs){
        try {
            //获取当前线程访问的请求属性
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            //获取当前属性中的SessionId
            String sessionId = RequestContextHolder.getRequestAttributes().getSessionId();
            //获取属性中的服务器请求
            HttpServletRequest request = attributes.getRequest();
            String key = sessionId + "-" + request.getServletPath();
            if (cache.getIfPresent(key) == null) {// 如果缓存中有这个url视为重复提交
                Object o = null;
                o = pjp.proceed();
                cache.put(key, 0);
                return o;
            } else {
                throw new ShipException(LoginStatusEnums.LOGIN_ERROR_MORE.getCode(),LoginStatusEnums.LOGIN_ERROR_MORE.getMessage());
            }
        }catch (Throwable e){

            return ResultVOUtil.error(-1,e.getMessage());
        }
    }
}

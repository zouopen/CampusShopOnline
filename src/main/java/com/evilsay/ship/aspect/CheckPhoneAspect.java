package com.evilsay.ship.aspect;

import com.evilsay.ship.Annotation.CheckPhoneSubmit;
import com.evilsay.ship.Utils.CheckPhone;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Author: EvilSay
 * @Date: 2019/4/27 15:58
 */
@Aspect
@Component
public class CheckPhoneAspect {


    @Around("execution(* com.evilsay.ship.Controller..*(..)) && @annotation(nrs)")
    public void aroundPhoneCheck(ProceedingJoinPoint proceedingJoinPoint,CheckPhoneSubmit nrs){

    }
}

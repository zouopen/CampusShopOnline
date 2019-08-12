package com.evilsay.ship.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: EvilSay
 * @Date: 2019/4/27 15:57
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPhoneSubmit {

    boolean phoneNumber() default false;

}

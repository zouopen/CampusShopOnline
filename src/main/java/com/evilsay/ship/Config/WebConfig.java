package com.evilsay.ship.Config;

import com.evilsay.ship.Config.xss.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: EvilSay
 * @Date: 2019/2/14 17:50
 */
@Configuration
public class WebConfig {
    /**
     * xssFilter注册
     */
    @Bean
    public FilterRegistrationBean xssFilterRegistration() {
            FilterRegistrationBean registration = new FilterRegistrationBean(new XssFilter());
            registration.addUrlPatterns("/*");
        return registration;
    }

}

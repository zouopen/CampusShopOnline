package com.evilsay.ship.Config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @Author: EvilSay
 * @Date: 2019/4/14 21:05
 */
@Configuration
public class UrlCacheConfig {
    @Bean
    public Cache<String,Integer> getCacheCheck(){
        //定时为2分钟
        return CacheBuilder.newBuilder().expireAfterWrite(2L,TimeUnit.SECONDS).build();
    }
}

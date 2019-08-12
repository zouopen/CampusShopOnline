package com.evilsay.ship.Config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/** 微信第三方登录配置
 * @Author: EvilSay
 * @Date: 2019/2/7 16:33
 */
@Component
public class WeChatOpenConfig {

    @Autowired
    private WeChatAccountConfig weChatAccountConfig;

    @Bean
    public WxMpService wxOpenService(){
        WxMpService wxMpService = new WxMpServiceImpl();

        wxMpService.setWxMpConfigStorage(wxOpenConfigStorage());

        return wxMpService;
    }
    @Bean
    public WxMpConfigStorage wxOpenConfigStorage(){
        WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();

        wxMpInMemoryConfigStorage.setAppId(weChatAccountConfig.getOpenAppId());

        wxMpInMemoryConfigStorage.setSecret(weChatAccountConfig.getOpenAppSecret());

        return wxMpInMemoryConfigStorage;
    }
}

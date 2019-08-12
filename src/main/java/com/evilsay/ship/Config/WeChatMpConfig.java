package com.evilsay.ship.Config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/** @see 详情请参见https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140842
 * @Author: EvilSay
 * @Date: 2019/1/28 21:22
 */
@Component // 管理Spring容器里的Bean
public class WeChatMpConfig {
    @Autowired
    private WeChatAccountConfig weChatAccountConfig;
    //把该类注册到Spring容器里
    @Bean
    public WxMpService wxMpService(){
        WxMpService wxMpService = new WxMpServiceImpl();
        //微信客户端配置存储
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }
    @Bean
    public WxMpConfigStorage wxMpConfigStorage(){
        //基于内存的微信配置
        WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage =new WxMpInMemoryConfigStorage();
        //获取APPId
        wxMpInMemoryConfigStorage.setAppId(weChatAccountConfig.getMpAppId());
        //获取setSecret
        wxMpInMemoryConfigStorage.setSecret(weChatAccountConfig.getMpAppSecret());
        return wxMpInMemoryConfigStorage;
    }
}

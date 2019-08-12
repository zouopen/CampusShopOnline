package com.evilsay.ship.Config;

import cn.jpush.api.JPushClient;
import cn.jpush.api.JPushConfig;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author: EvilSay
 * @Date: 2019/5/4 2:11
 */
@Component
public class JPlushMpConfig {
    @Autowired
    private JPlushConfig jPlushConfig;

    @Bean
    public JPushClient jPushClient(){
        return new JPushClient(jPlushConfig.getMasterSecret(),jPlushConfig.getAppKey());
    }

}

package com.evilsay.ship.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: EvilSay
 * @Date: 2019/5/4 1:51
 */
@Component
@Data
@ConfigurationProperties(prefix = "plush")
public class JPlushConfig  {

    private String appKey;

    private String masterSecret;

    private String groupPushKey;

    private String groupMasterKey;


}

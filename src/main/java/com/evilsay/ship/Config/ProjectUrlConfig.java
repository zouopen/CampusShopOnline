package com.evilsay.ship.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: EvilSay
 * @Date: 2019/2/7 21:07
 */
@Data
@ConfigurationProperties(prefix = "projectUrl")
@Component
public class ProjectUrlConfig {
    // 微信公众平台授权URL
    public String wechatMpAuthorize;
    //微信开放平台的URL
    public String wechatOpneAuthorize;
    //前端URL
    public String PlatformUrl;
}

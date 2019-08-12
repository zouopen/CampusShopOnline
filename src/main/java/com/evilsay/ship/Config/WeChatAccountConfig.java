package com.evilsay.ship.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/** yml下的配置文件
 * @Author: EvilSay
 * @Date: 2019/1/28 21:28
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WeChatAccountConfig {
    //公众号平台ID
    private String mpAppId;
    //公众号平台密钥
    private String mpAppSecret;
    //微信开放平台ID
    private String openAppId;
    //微信开放平台ID密钥
    private String openAppSecret;
    //商户号
    private String mchId;
    //密钥
    private String mchKey;
    //证书路径
    private String keyPath;
    //微信异步同步通知地址
    private String notifyUrl;
}

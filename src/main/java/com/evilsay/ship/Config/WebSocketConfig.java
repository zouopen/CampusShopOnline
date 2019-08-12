package com.evilsay.ship.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;



/**
 * @Author: EvilSay
 * @Date: 2019/2/25 19:52
 */

@Component
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter( ){
        return new ServerEndpointExporter();
    }
}

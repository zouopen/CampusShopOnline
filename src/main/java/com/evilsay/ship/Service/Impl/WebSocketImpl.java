package com.evilsay.ship.Service.Impl;

import com.evilsay.ship.DTO.ShiroUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author: EvilSay
 * @Date: 2019/2/25 12:33
 */
@Component
@Slf4j
@ServerEndpoint("/webSocket")
public class WebSocketImpl {

    private Session session;


    private static CopyOnWriteArraySet<WebSocketImpl> webSockets = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSockets.add(this);
        log.info("目前连接人数={}", webSockets.size());
    }

    @OnClose
    public void OnClose() {
        webSockets.remove(this);
        log.info("[websocket消息]连接断开:{}", webSockets.size());
    }

    @OnMessage
    public void onMessage(String message) {

        log.info("[websocket消息]收到客户端发来的消息:{}", message);
    }

    //    发送之前判断当前shopType
    void sendMessage(String message, Integer OrdershopType) {

        for (WebSocketImpl webSocket : webSockets) {
            log.info("[websocket]广播消息,message={}", message);
            ShiroUserDTO shiroUserDTO = (ShiroUserDTO) webSocket.session.getUserPrincipal();
            if (shiroUserDTO.getShopType().equals(OrdershopType)) {
                try {
                    webSocket.session.getBasicRemote().sendText(message);
                } catch (Exception e) {
                    log.error("[websocket消息]发送消息异常={}", e);
                }
            }
        }
    }
}

package com.evilsay.ship.Service;

import cn.jpush.api.push.model.PushPayload;

/**
 * @Author: EvilSay
 * @Date: 2019/5/4 2:25
 */
public interface PushPayloadService {
    //推送指定IDApp

    void sendAppointApp(String message,String ID);


}

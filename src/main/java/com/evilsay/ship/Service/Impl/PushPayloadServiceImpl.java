package com.evilsay.ship.Service.Impl;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.evilsay.ship.Config.JPlushMpConfig;
import com.evilsay.ship.Service.PushPayloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: EvilSay
 * @Date: 2019/5/4 2:28
 */
@Service
public class PushPayloadServiceImpl implements PushPayloadService {
    @Autowired
    private JPlushMpConfig jPlushMpConfig;
    @Override
    public void sendAppointApp(String message, String ID) {

        try {
            Message.Builder builder = new Message.Builder();

            builder.setContentType(message);

            PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.android())
                    .setAudience(Audience.alias(ID))
                    .setNotification(Notification.alert(message))
                    .build();

            JPushClient jPushClient = jPlushMpConfig.jPushClient();

            jPushClient.sendPush(payload);

            jPushClient.close();

        } catch (APIConnectionException | APIRequestException e) {
            e.printStackTrace();
        }
    }
}

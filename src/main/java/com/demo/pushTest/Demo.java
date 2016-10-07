package com.demo.pushTest;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

/*

-----------------网络延迟问题
APIConnectionException error
cn.jpush.api.common.resp.APIConnectionException: Read timed out.
Read response from JPush Server timed out.
If this is a Push action, you may not want to retry.
It may be due to slowly response from JPush server, or unstable connection.
If the problem persists, please let us know at support@jpush.cn.
	at cn.jpush.api.common.connection.NativeHttpClient.doRequest(NativeHttpClient.java:103)
	at cn.jpush.api.common.connection.NativeHttpClient.sendPost(NativeHttpClient.java:85)
	at cn.jpush.api.push.PushClient.sendPush(PushClient.java:160)
	at cn.jpush.api.JPushClient.sendPush(JPushClient.java:184)
	at com.demo.pushTest.Demo.push(Demo.java:41)
	at com.demo.pushTest.Demo.main(Demo.java:28)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:606)
	at com.intellij.rt.execution.application.AppMain.main(AppMain.java:147)
Caused by: java.net.SocketTimeoutException: Read timed out
	at cn.jpush.api.common.connection.NativeHttpClient._doRequest(NativeHttpClient.java:240)
	at cn.jpush.api.common.connection.NativeHttpClient.doRequest(NativeHttpClient.java:98)
	... 10 more

 */
public class Demo {
    public static void main(String[] args) {
        push();
    }
    private static void push() {
        String masterSecret = "c1cb8e69bde5dee4ba6fb10c";
        String appkey = "b3a4cdcd5bdb9d6245165397";
        JPushClient jPushClient = new JPushClient(masterSecret, appkey);
        PushPayload pushPayload=PushPayload.newBuilder()
                .setPlatform(Platform.all())            //平台
                .setAudience(Audience.alias("lin"))     //设置自己手机alians:lin,给自己手机推送信息
                .setNotification(Notification.alert("Notification 别名Test"))
                .setMessage(Message.content("Message 别名Test"))
                .build();
        try {
            PushResult result=jPushClient.sendPush(pushPayload);
            System.out.println(result.msg_id);
            System.out.println(result.sendno);
        } catch (APIConnectionException e) {
            System.out.println("APIConnectionException error");
            e.printStackTrace();
        } catch (APIRequestException e) {
            System.out.println("APIRequestException error");
            e.printStackTrace();
        }
    }
}

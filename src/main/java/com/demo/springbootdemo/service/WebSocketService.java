package com.demo.springbootdemo.service;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.demo.springbootdemo.pojo.WebSocketMessage;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
@ServerEndpoint("/ws/{userId}")
public class WebSocketService {
    // 在线链接数
    private static final AtomicInteger onlineCount = new AtomicInteger();

    //存放每个客户端对应的WebSocketServer对象
    private static final ConcurrentHashMap<String, WebSocketService> webSocketMap = new ConcurrentHashMap<>();

    //与某个客户端的链接会话
    private Session session;

    // 接受的用户ID
    private String userId;

    //最后心跳时间
    private long lastHeartbeatTime = System.currentTimeMillis();

    // 链接建立成功调用的方法
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            webSocketMap.put(userId, this);
        } else {
            webSocketMap.put(userId, this);
            addOnlineCount();
        }
        log.info("用户连接：{}，当前在线人数为：{}", userId, getOnlineCount());
        new HeartbeatThread().start();
    }

    // 链接关闭调用的方法

    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            subOnlineCount();
        }
        log.info("用户退出：{}，当前在线人数为：{}", userId, getOnlineCount());
    }


    /*
     * 收到客户端消息后调用的方法
     * */

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("用户消息：{}，内容：{}", userId, message);
        JSONObject jsonObj = JSONUtil.parseObj(message);
        String messageType = jsonObj.getStr("type");
        String toUser = jsonObj.getStr("to");
        log.info("用户消息：{}，内容：{}", userId, messageType);
        if ("heartbeat".equals(messageType)) {
            this.lastHeartbeatTime = System.currentTimeMillis();
            return;
        }
        switch (messageType) {
            case "unicast":
                sendToUser(toUser, message);
                break;
            case "broadcast":
                broadcast(message);
                break;
            default:
                log.warn("未知的消息类型:{}", messageType);

        }

    }

    /**
     * 出现错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误：{}，原因：{}", session, error.getMessage());
    }

    /**
     * 发送消息给指定用户
     */
    public static void sendToUser(String toUserId, String message) {
        if (webSocketMap.containsKey(toUserId)) {
            webSocketMap.get(toUserId).sendMessage(message);
        } else {
            log.warn("用户{}不在线", toUserId);
        }
    }

    public static void broadcast(String message) {
        webSocketMap.forEach((userId, webSocket) -> {
            if (webSocket.session.isOpen()) {
                webSocket.sendMessage(message);
            } else {
                log.warn("用户{}不在线，消息发送失败", userId);
            }
        });
    }

    private void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (Exception e) {
            log.warn("用户{}不在线，消息发送失败", e.getMessage());
        }
    }

    private static synchronized void subOnlineCount() {
        onlineCount.decrementAndGet();
    }

    public static synchronized int getOnlineCount() {
        return onlineCount.get();
    }

    private static synchronized void addOnlineCount() {
        onlineCount.decrementAndGet();
    }

    /**
     * 心跳检测线程
     */
    private class HeartbeatThread extends Thread {
        @Override
        public void run() {

            while (session.isOpen()) {

                try {
                    // 每30秒检测一次
                    Thread.sleep(30000);

                    // 超过60秒未收到心跳，关闭连接
                    if (System.currentTimeMillis() - lastHeartbeatTime > 60000) {
                        session.close();
                        break;
                    }
                } catch (Exception e) {
                    log.error("心跳检测异常:{}", e.getMessage());
                    break;
                }
            }
        }
    }

}

package com.demo.springbootdemo.pojo;

import lombok.Data;

@Data
public class WebSocketMessage {
    private String type; // 消息类型：heartbeat/unicast/broadcast
    private String from;      // 发送者ID
    private String to;        // 接收者ID（单播时使用）WebSocketMessage
    private String content;    // 消息内容
    private Long timestamp;   // 时间戳
}

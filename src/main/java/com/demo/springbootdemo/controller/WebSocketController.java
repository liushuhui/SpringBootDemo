package com.demo.springbootdemo.controller;

import com.demo.springbootdemo.service.WebSocketService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ws")
public class WebSocketController {
    @PostMapping("/sendToUser")
    public String sendToUser(@RequestParam String toUserId, @RequestParam String message) {
        WebSocketService.sendToUser(toUserId, message);
        return "消息发送成功";
    }

    @PostMapping("/broadcast")
    public String broadcast(@RequestParam String message) {
        WebSocketService.broadcast(message);
        return "广播消息发送成功";
    }
}

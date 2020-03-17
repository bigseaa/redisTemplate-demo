package com.bigsea.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public static final String CHAT_NAME = "myMessage";

    private static final int MESSAGE_COUNT = 10;

    @GetMapping("/pub")
    public void publish() {
        for(int i = 1; i <= MESSAGE_COUNT; i++) {
            stringRedisTemplate.convertAndSend(CHAT_NAME, "发布的第" + i + "条消息");
        }
    }
}

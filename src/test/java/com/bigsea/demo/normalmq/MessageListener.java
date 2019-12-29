package com.bigsea.demo.normalmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * 使用redis使用普通消息队列，该队列为先进先出，并且每一个消息只能被一个消费者消费
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageListener {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 消费队列
     */
    @Test
    public void listenMessage() {
        String message = redisTemplate.opsForList().leftPop("testList", 1000, TimeUnit.MINUTES);
        System.out.println(message);
    }
}

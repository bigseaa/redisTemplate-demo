package com.bigsea.demo.normalmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * 使用redis实现简单消息队列，该队列为先进先出，并且每一个消息只能被一个消费者消费
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
        while(true) {
            try {
                String message = redisTemplate.opsForList().leftPop("simpleQueue", 60, TimeUnit.SECONDS);
                System.out.println("已消费队列" + message);
            } catch (QueryTimeoutException e) {
                System.out.println("60秒内没有新的消息，继续。。。");
            }
        }
    }
}

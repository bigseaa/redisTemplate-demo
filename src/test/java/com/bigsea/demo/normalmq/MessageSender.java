package com.bigsea.demo.normalmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 使用redis实现普通消息队列，该队列为先进先出，并且每一个消息只能被一个消费者消费
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageSender {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 向队列发送消息（往list的最右边添加元素）
     */
    @Test
    public void sendMessage() {
        // 往队列的最右边添加元素
        redisTemplate.opsForList().rightPush("testList", "java");
        redisTemplate.opsForList().rightPush("testList", "c++");
        redisTemplate.opsForList().rightPush("testList", "python");
    }

    @Test
    public void remove() {
        redisTemplate.delete("testList");
    }
}

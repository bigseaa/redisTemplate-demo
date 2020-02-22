package com.bigsea.demo.delaymq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Random;

/**
 * 使用redis实现延时消息队列，实现方式使用sorted-set的数据结构
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
        // 时间戳取五秒后的数据，存入sorted-set的score值
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 5);
        double millisecond = calendar.getTimeInMillis();
        // 以简单的方式模拟订单号
        Random random = new Random();
        int orderId = Math.abs(random.nextInt());
        redisTemplate.opsForZSet().add("orderId", String.valueOf(orderId), millisecond );
        System.out.println("发送订单任务，订单ID为===============" + orderId);
    }
}

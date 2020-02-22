package com.bigsea.demo.delaymq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.Set;

/**
 * 使用redis实现延时队列，实现方式使用sorted-set的数据结构
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
        while(true){
            Set<ZSetOperations.TypedTuple<String>> orderIdSet = redisTemplate.opsForZSet().rangeWithScores("orderId", 0, -1);
            if(orderIdSet == null || orderIdSet.isEmpty()){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
                continue;
            }
            Iterator<ZSetOperations.TypedTuple<String>> iterator = orderIdSet.iterator();
            ZSetOperations.TypedTuple<String> next = iterator.next();
            Double score = next.getScore();
            if(score == null) {
                continue;
            }
            double nowTime = System.currentTimeMillis();
            if(nowTime >= score) {
                String value = next.getValue();
                redisTemplate.opsForZSet().remove("orderId", value);
                System.out.println("已成功处理一条订单，订单id为" + value);
            }
        }
    }
}

package com.bigsea.demo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 对于redis的数据类型的常用操作
 * redis共有五种数据类型，分别是String、Hash、List、Set、zset
 * 本类对list类型的操作进行测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ListRedisTemplateTests {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 操作字符串
     */
    @Test
    public void testStr() {
        redisTemplate.opsForValue().set("testStr", "你好，世界！");
        String str = redisTemplate.opsForValue().get("testStr");
        System.out.println(str);
    }

    /**
     * 测试List One
     */
    @Test
    public void testListOne() {
        // 往队列的最右边添加元素
        redisTemplate.opsForList().rightPush("testList", "java");
        redisTemplate.opsForList().rightPush("testList", "c++");
        redisTemplate.opsForList().rightPush("testList", "python");
        Long testListSize = redisTemplate.opsForList().size("testList");
        if(testListSize != null && testListSize > 0) {
            for(int i = 0; i < testListSize; i++) {
                // 弹出队列最左边的一个元素
                String element = redisTemplate.opsForList().leftPop("testList");
                System.out.println(element);
            }
        }
    }
}

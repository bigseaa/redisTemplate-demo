package com.bigsea.demo.key;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConstructData {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void constructData() {
        for(int i = 0; i < 100; i++) {
            redisTemplate.opsForValue().set("key" + i, "123456");
        }
    }
}

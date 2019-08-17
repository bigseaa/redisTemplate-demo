package com.bigsea.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDemoApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 操作字符串
     */
    @Test
    public void testStr() {
        redisTemplate.opsForValue().set("testStr", "你好，世界！");
        Object obj = redisTemplate.opsForValue().get("testStr");
        System.out.println(obj);
    }

    @Test
    public void testList() {
        redisTemplate.opsForList().leftPush("testList", "java");
        redisTemplate.opsForList().leftPush("testList", "c++");
        redisTemplate.opsForList().leftPush("testList", "python");
        while(redisTemplate.opsForList().size("testList") > 0) {
            System.out.println(redisTemplate.opsForList().leftPop("testList"));
        }
    }

    /**
     * 操作hash
     */
    @Test
    public void testHash() {
        redisTemplate.opsForHash().put("testMap", "testKey", "这里是值！");
        Boolean hasKey = redisTemplate.opsForHash().hasKey("testMap", "testKey");
        if(hasKey) {
            Object o = redisTemplate.opsForHash().get("testMap", "testKey");
            System.out.println(o);
        }
    }

    /**
     * 操作set
     */
    @Test
    public void testSet() {
        redisTemplate.opsForSet().add("testSet", "bbb", "aaaa", "ccc");
        while(redisTemplate.opsForSet().size("testSet") > 0) {
            System.out.println(redisTemplate.opsForSet().pop("testSet"));
        }
    }

    /**
     * 操作sort set
     */
    @Test
    public void testZSet() {

    }


}

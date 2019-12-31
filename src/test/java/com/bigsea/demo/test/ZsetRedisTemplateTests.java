package com.bigsea.demo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 对于redis的数据类型的常用操作
 * redis共有五种数据类型，分别是String、Hash、List、Set、zset
 * 本列对于zset类数据的操作进行测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ZsetRedisTemplateTests {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 打印zset中的元素
     * @param key String
     */
    private void printZSet(String key) {
        // 按照分数大小(从小到大)打印指定区间内的元素, -1为打印全部
        // range的后两个参数不是分数，而是索引，zset中的数据已经排序，可以按下标索引去查
        Set<String> range = redisTemplate.opsForZSet().range(key, 0, -1);
        // 打印，score从大到小
        System.out.println(range);
    }

    /**
     * zet中添加元素
     */
    @Test
    public void testAdd() {
        // 向集合中插入元素，并设置score
        redisTemplate.opsForZSet().add("order", "o1", 2.1);
        // 向集合中插入多个元素
        DefaultTypedTuple<String> tuple1 = new DefaultTypedTuple<>("o2", 1.1);
        DefaultTypedTuple<String> tuple2 = new DefaultTypedTuple<>("o3", 3.1);
        redisTemplate.opsForZSet().add("order", new HashSet<>(Arrays.asList(tuple1, tuple2)));
        //打印
        printZSet("order");
    }

    /**
     * 删除zset中的元素
     */
    @Test
    public void testRemove() {
        printZSet("order");
        // 从集合中删除指定元素（根据key）
        redisTemplate.opsForZSet().remove("order", "o1");
        printZSet("order");
    }

    /**
     * 返回指定元素的排名（不是分数，是根据分数后排序的排名）
     */
    @Test
    public void testRank() {
        // 返回指定成员的排名（从小到大）
        Long rank = redisTemplate.opsForZSet().rank("order", "o2");
        // 从大到小
        Long reverseRank = redisTemplate.opsForZSet().reverseRank("order", "o2");
        System.out.println(rank);
        System.out.println(reverseRank);
    }

    /**
     * 根据分数从小到大的顺序查询集合中的元素，打印的内容为值与分数
     */
    @Test
    public void testRankWithScores() {
        Set<ZSetOperations.TypedTuple<String>> tuples = redisTemplate.opsForZSet().rangeWithScores("order", 0, -1);
        if(tuples == null) {
            return;
        }
        for (ZSetOperations.TypedTuple<String> tuple : tuples) {
            System.out.println(tuple.getValue() + " : " + tuple.getScore());
        }
    }

    /**
     * 指定分数范围并按从小到大的顺序返回
     */
    @Test
    public void testRangeByScore() {
        // 以从小到大的顺序返回指定的score内的元素
        Set<String> ranking = redisTemplate.opsForZSet().rangeByScore("order", 0, 8);
        System.out.println(ranking);
        // 以从小到大的顺序返回指定的score内的元素，下面一行代码就表示指定分数范围为0到5，并且从这一个范围内的第二个元素开始取，取三个元素
        Set<String> ranking2 = redisTemplate.opsForZSet().rangeByScore("order", 0, 8, 1, 3);
        System.out.println(ranking2);
    }

    /**
     * 查询指定集合中的元素个数
     */
    @Test
    public void testCount() {
        // 返回集合内指定分数范围内的元素个数
        Long count = redisTemplate.opsForZSet().count("order", 0, 2);
        System.out.println(count);
        // 返回集合中的元素个数
        Long size = redisTemplate.opsForZSet().size("order");
        System.out.println(size);
    }

    /**
     * 获取指定元素的score
     */
    @Test
    public void testGetScore() {
        //获得指定元素的分数
        Double score = redisTemplate.opsForZSet().score("order", "p1");
        System.out.println(score);
    }

    /**
     * 删除指定索引范围内的元素（是索引，不是分数）
     */
    @Test
    public void testRemoveRange() {
        printZSet("order");
        redisTemplate.opsForZSet().removeRange("order", 0, 0);
        printZSet("order");
    }

    /**
     * 删除指定score范围内的元素
     */
    @Test
    public void testRemoveRangeByScore() {
        printZSet("order");
        redisTemplate.opsForZSet().removeRangeByScore("order", 4, 5);
        printZSet("order");
        redisTemplate.opsForZSet();
    }
}

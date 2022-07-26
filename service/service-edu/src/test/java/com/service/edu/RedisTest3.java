package com.service.edu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

/**
 * @author alpha
 * @className: RedisTest3
 * @date 2022/7/25 23:24
 * @Description
 */
@SpringBootTest
public class RedisTest3 {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void testHash() {
        BoundHashOperations<String, String, String> hashOps = stringRedisTemplate.boundHashOps("teacher:delFail:avatar");
        hashOps.put("ss.jpg", "avatar");
        hashOps.put("cc.jpg", "avatar");
        hashOps.put("ff.jpg", "avatar");
        System.out.println("hashOps.size() = " + hashOps.size());

        Map<String, String> entries = hashOps.entries();
        entries.forEach((k, v) -> {
            System.out.println("k:" + k + "v:" + v);
        });
        System.out.println("hashOps.keys() = " + hashOps.keys());
        System.out.println("hashOps.values() = " + hashOps.values());
        hashOps.delete("cc.jpg");
        System.out.println("hashOps.size() = " + hashOps.size());
    }

    @Test
    void testList(){
        BoundListOperations<String, String> listkey = stringRedisTemplate.boundListOps("listkey");
        System.out.println("listkey.size() = " + listkey.size());
        listkey.leftPush("fangfang");
        listkey.leftPush("ccc");
        System.out.println("listkey.size() = " + listkey.size());
        System.out.println("listkey.index(0) = " + listkey.index(0));
        System.out.println("listkey.rightPop() = " + listkey.rightPop());
        System.out.println("listkey.size() = " + listkey.size());

    }
}

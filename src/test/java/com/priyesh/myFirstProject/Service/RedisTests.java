package com.priyesh.myFirstProject.Service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Disabled
    @Test
    public void redisTes(){
        redisTemplate.opsForValue().set("email", "priyesh@gmail.com");
        Object name = redisTemplate.opsForValue().get("name");
        int a = 1;
    }
}

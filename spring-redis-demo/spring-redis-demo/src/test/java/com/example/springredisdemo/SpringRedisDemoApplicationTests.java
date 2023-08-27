package com.example.springredisdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringRedisDemoApplicationTests {


    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Test
    void contextLoads() throws InterruptedException {


        while (true) {
            BoundValueOperations<String, String> test1 = redisTemplate.boundValueOps("test");
            test1.set("hello");
            String test = test1.get();
            System.out.println(test);
            Thread.sleep(1000);
        }
    }

}

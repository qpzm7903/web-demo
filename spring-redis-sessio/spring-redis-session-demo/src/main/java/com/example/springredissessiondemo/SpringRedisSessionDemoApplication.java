package com.example.springredissessiondemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
public class SpringRedisSessionDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRedisSessionDemoApplication.class, args);
    }

}

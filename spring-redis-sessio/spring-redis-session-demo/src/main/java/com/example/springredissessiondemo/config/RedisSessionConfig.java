package com.example.springredissessiondemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.FlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * todo description
 *
 * @author qpzm7903
 * @since 2022-10-30-22:26
 */
@Configuration
@EnableRedisHttpSession(flushMode = FlushMode.IMMEDIATE)
public class RedisSessionConfig {
    @Bean(name = "springSessionDefaultRedisSerializer")
    public RedisSerializer springSessionDefaultRedisSerializer() {
        return RedisSerializer.json();
    }
}

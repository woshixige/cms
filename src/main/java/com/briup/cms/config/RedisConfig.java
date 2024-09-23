package com.briup.cms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        //设置序列化 反序列
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //hash key value
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        //设置工厂连接对象
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}

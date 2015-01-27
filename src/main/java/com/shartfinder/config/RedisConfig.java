package com.shartfinder.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.shartfinder.encounter.command.framework.EncounterEvent;

@Configuration
public class RedisConfig {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName("pub-redis-18240.us-east-1-3.1.ec2.garantiadata.com");
        factory.setPort(18240);
        factory.setPassword("abc123");
        factory.setUsePool(true);
        return factory;
    }

    @Bean
    StringRedisTemplate redisTemplate() {
        StringRedisTemplate redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }

    @Bean
    RedisTemplate<String, EncounterEvent> redisTemplateTableEvent() {
        RedisTemplate<String, EncounterEvent> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }

    @Bean
    CacheManager cacheManager() {
        return new RedisCacheManager(redisTemplate());
    }

}

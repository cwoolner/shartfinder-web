package com.shartfinder.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.shartfinder.encounter.command.framework.EncounterCommand;
import com.shartfinder.encounter.command.framework.EncounterEvent;
import com.shartfinder.encounter.command.framework.EncounterEventType;
import com.shartfinder.framework.event.EventSubscriber;

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
    RedisTemplate<String, EncounterCommand> redisTemplateEncounterCommand() {
        RedisTemplate<String, EncounterCommand> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(
                EncounterCommand.class));
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }

    @Bean
    RedisTemplate<String, EncounterEvent> redisTemplateEncounterEvent() {
        RedisTemplate<String, EncounterEvent> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(
                EncounterCommand.class));
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }

    @Bean
    CacheManager cacheManager() {
        return new RedisCacheManager(redisTemplate());
    }

    @Bean
    RedisMessageListenerContainer container(JedisConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("*"));
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(EventSubscriber<EncounterEventType> subscriber) {
        MessageListenerAdapter listener = new MessageListenerAdapter(subscriber,
                "receive");
        listener.setSerializer(new Jackson2JsonRedisSerializer<>(EncounterEvent.class));
        return listener;
    }

}

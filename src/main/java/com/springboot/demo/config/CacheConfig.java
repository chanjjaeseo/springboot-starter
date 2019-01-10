package com.springboot.demo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

//@Configuration
public class CacheConfig {

    @Value("${spring.redis.sentinel.master}")
    private String sentinelMasterName = null;

    @Value("${spring.redis.sentinel.nodes}")
    private String hostAndPorts = null;

    public String getSentinelMasterName() {
        return sentinelMasterName;
    }

    public void setSentinelMasterName(String sentinelMasterName) {
        this.sentinelMasterName = sentinelMasterName;
    }

    public String getHostAndPorts() {
        return hostAndPorts;
    }

    public void setHostAndPorts(String hostAndPorts) {
        this.hostAndPorts = hostAndPorts;
    }

    @Primary
    @Bean("redisConnection")
    @ConfigurationProperties("spring.redis")
    public JedisConnectionFactory redisConnection(@Qualifier("redisPool") JedisPoolConfig poolConfig) {
        Set<String> sentinelHostAndPorts = StringUtils.commaDelimitedListToSet(this.getHostAndPorts());
        RedisSentinelConfiguration sc = new RedisSentinelConfiguration(this.getSentinelMasterName(),
                sentinelHostAndPorts);
        return new JedisConnectionFactory(sc, poolConfig);
    }


    @Bean("redisPool")
    @ConfigurationProperties(prefix = "spring.redis.pool")
    public JedisPoolConfig redisPool() {
        return new JedisPoolConfig();
    }

    @Primary
    @Bean("redisTemplate")
    public RedisTemplate<String, String> redisTemplate(
            @Qualifier("redisConnection") RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        return redisTemplate;
    }

}

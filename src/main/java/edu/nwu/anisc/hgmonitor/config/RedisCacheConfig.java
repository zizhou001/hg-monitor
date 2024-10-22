package edu.nwu.anisc.hgmonitor.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.nwu.anisc.hgmonitor.adapter.CacheTtlAdapter;
import edu.nwu.anisc.hgmonitor.bo.CacheNameWithTtlBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 缓存配置类
 *
 * @author zizhou
 * @version 1.0.0
 * @date 2024-10-22 15:04
 */
@Configuration
@ConditionalOnProperty(name = "app.cache.provider", havingValue = "redis", matchIfMissing = false)
public class RedisCacheConfig {

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Autowired
    CacheTtlAdapter cacheTtlAdapter;

    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory,
                                               CacheTtlAdapter cacheTtlAdapter) {
        return new RedisCacheManager(
                RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
                // 未配置的key使用默认策略
                this.getRedisCacheConfigurationWithTtl(3600),
                // 指定的key使用特定策略
                this.getRedisCacheConfigurationMap(cacheTtlAdapter));
    }

    /**
     * 自定义 RedisTemplate 的序列化方式和其他配置
     *
     * @param redisConnectionFactory
     * @param redisSerializer
     * @return
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory,
                                                       RedisSerializer<Object> redisSerializer) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setDefaultSerializer(redisSerializer);
        template.setValueSerializer(redisSerializer);
        template.setHashValueSerializer(redisSerializer);
        template.setKeySerializer(StringRedisSerializer.UTF_8);
        template.setHashKeySerializer(StringRedisSerializer.UTF_8);
        template.afterPropertiesSet();
        return template;
    }

    /**
     * RedisTemplate 的一个子类，专门为字符串类型的键和值进行了优化。
     * 简化对 Redis 中字符串数据的操作，而不需要手动设置序列化器。
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return new StringRedisTemplate(redisConnectionFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    public CacheTtlAdapter cacheTtl() {
        return Collections::emptyList;
    }

    /**
     * 自定义redis序列化的机制,重新定义一个ObjectMapper.防止和MVC的冲突
     *
     * @return
     */
    @Bean
    public RedisSerializer<Object> redisSerializer() {
        ObjectMapper objectMapper = new ObjectMapper();
        // 反序列化时候遇到不匹配的属性并不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 序列化时候遇到空对象不抛出异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 反序列化的时候如果是无效子类型,不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
        // 不使用默认的dateTime进行序列化,
        objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
        // 使用JSR310提供的序列化类,里面包含了大量的JDK8时间序列化类
        objectMapper.registerModule(new JavaTimeModule());
        // 启用反序列化所需的类型信息,在属性中添加@class
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        // 配置null值的序列化器
        GenericJackson2JsonRedisSerializer.registerNullValueSerializer(objectMapper, null);
        return new GenericJackson2JsonRedisSerializer(objectMapper);
    }

    /**
     * 获取缓存清除策略配置map
     *
     * @param cacheTtlAdapter
     * @return Map<String, RedisCacheConfiguration>
     */
    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap(CacheTtlAdapter cacheTtlAdapter) {
        if (cacheTtlAdapter == null) {
            return Collections.emptyMap();
        }
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>(16);

        for (CacheNameWithTtlBO cacheNameWithTtlBO : cacheTtlAdapter.listCacheNameWithTtl()) {
            redisCacheConfigurationMap.put(cacheNameWithTtlBO.getCacheName(),
                    getRedisCacheConfigurationWithTtl(cacheNameWithTtlBO.getTtl()));
        }
        return redisCacheConfigurationMap;
    }

    /**
     * 封装ttl字段，返回redisCacheConfiguration实例
     *
     * @param seconds
     * @return RedisCacheConfiguration
     */
    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer()))
                .entryTtl(Duration.ofSeconds(seconds));

        return redisCacheConfiguration;
    }
}

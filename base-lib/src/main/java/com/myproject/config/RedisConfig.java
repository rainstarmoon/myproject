//package com.myproject.config;
//
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Configuration
//@EnableCaching
//public class RedisConfig {
//
//	@Bean
//	public CacheManager cacheManager(RedisConnectionFactory factory) {
//		// RedisCacheManager cacheManager = RedisCacheManager.create(factory);
//
//		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig(); // 生成一个默认配置，通过config对象即可对缓存进行自定义配置
//		config = config.entryTtl(Duration.ofMinutes(1)) // 设置缓存的默认过期时间，也是使用Duration设置
//				.disableCachingNullValues(); // 不缓存空值
//
//		// 设置一个初始化的缓存空间set集合
//		Set<String> cacheNames = new HashSet<>();
//		cacheNames.add("other");
//		cacheNames.add("user");
//
//		// 对每个缓存空间应用不同的配置
//		Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
//		configMap.put("other", config);
//		configMap.put("user", config.entryTtl(Duration.ofSeconds(120)));
//
//		RedisCacheManager cacheManager = RedisCacheManager.builder(factory) // 使用自定义的缓存配置初始化一个cacheManager
//				.initialCacheNames(cacheNames) // 注意这两句的调用顺序，一定要先调用该方法设置初始化的缓存名，再初始化相关的配置
//				.withInitialCacheConfigurations(configMap).build();
//		return cacheManager;
//	}
//
//	@Bean
//	public RedisTemplate<String, Object> redisCacheTemplate(LettuceConnectionFactory redisConnectionFactory) {
//		RedisTemplate<String, Object> template = new RedisTemplate<>();
//		template.setConnectionFactory(redisConnectionFactory);
//		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//		template.setKeySerializer(new StringRedisSerializer());
//		template.setHashKeySerializer(new StringRedisSerializer());
//		return template;
//	}
//
//}

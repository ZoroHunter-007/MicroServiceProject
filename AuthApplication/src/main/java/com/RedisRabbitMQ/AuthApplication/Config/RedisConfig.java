package com.RedisRabbitMQ.AuthApplication.Config;

import java.time.Duration;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

	@Bean
	public RedisTemplate<String, Object>redisTemplate(RedisConnectionFactory  connectionFactory){
		RedisTemplate<String, Object>template=new RedisTemplate<String, Object>();
		
		
		template.setConnectionFactory(connectionFactory);
		return template;
	}
	
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
		RedisCacheConfiguration configuration=RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofMinutes(5))
				.disableCachingNullValues();
		return RedisCacheManager.builder(connectionFactory)
				.cacheDefaults(configuration)
				.build();
	}
}

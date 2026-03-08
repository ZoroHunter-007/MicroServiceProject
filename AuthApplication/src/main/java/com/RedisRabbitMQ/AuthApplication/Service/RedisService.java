package com.RedisRabbitMQ.AuthApplication.Service;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisService {

	private final RedisTemplate<String ,Object>redisTemplate;
	
	public void saveToken(String email,String token) {
		
		redisTemplate.opsForValue()
		.set(email, token,30,TimeUnit.MINUTES);
	}
	
	public String getToken(String email) {
		return (String) redisTemplate.opsForValue().get(email);
	}
	
	public void deleteToken(String email) {
		redisTemplate.delete(email);
	}
}

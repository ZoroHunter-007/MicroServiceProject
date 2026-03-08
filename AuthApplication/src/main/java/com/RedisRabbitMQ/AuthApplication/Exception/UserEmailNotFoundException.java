package com.RedisRabbitMQ.AuthApplication.Exception;

public class UserEmailNotFoundException extends RuntimeException{

	public UserEmailNotFoundException(String message) {
		super(message);
	}
}

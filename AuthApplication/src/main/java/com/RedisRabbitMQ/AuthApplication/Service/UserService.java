package com.RedisRabbitMQ.AuthApplication.Service;

import java.util.List;

import com.RedisRabbitMQ.AuthApplication.DTO.AuthResponse;
import com.RedisRabbitMQ.AuthApplication.DTO.LoginRequest;
import com.RedisRabbitMQ.AuthApplication.DTO.LoginResponse;
import com.RedisRabbitMQ.AuthApplication.DTO.RegisterRequest;
import com.RedisRabbitMQ.AuthApplication.DTO.UserResponse;


public interface UserService {

	AuthResponse<LoginResponse>login(LoginRequest dto);
	
	AuthResponse<UserResponse> register(RegisterRequest dto);
	
	AuthResponse<List<UserResponse>>getAllUser();
	
	AuthResponse<UserResponse>getUserByEmail(String email);
	
	AuthResponse<UserResponse>updateUser(RegisterRequest dto,String email);
	
	AuthResponse<UserResponse>deleteUserByEmail(String email);
}

package com.MicroServiceProject.AuthApplication.Service;

import java.util.List;

import com.MicroServiceProject.AuthApplication.DTO.AuthResponse;
import com.MicroServiceProject.AuthApplication.DTO.LoginRequest;
import com.MicroServiceProject.AuthApplication.DTO.LoginResponse;
import com.MicroServiceProject.AuthApplication.DTO.RegisterRequest;
import com.MicroServiceProject.AuthApplication.DTO.UserResponse;


public interface UserService {

	AuthResponse<LoginResponse>login(LoginRequest dto);
	
	AuthResponse<UserResponse> register(RegisterRequest dto);
	
	AuthResponse<List<UserResponse>>getAllUser();
	
	AuthResponse<UserResponse>getUserByEmail(String email);
	
	AuthResponse<UserResponse>updateUser(RegisterRequest dto,String email);
	
	AuthResponse<UserResponse>deleteUserByEmail(String email);
}

package com.RedisRabbitMQ.AuthApplication.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.RedisRabbitMQ.AuthApplication.DTO.AuthResponse;
import com.RedisRabbitMQ.AuthApplication.DTO.LoginRequest;
import com.RedisRabbitMQ.AuthApplication.DTO.LoginResponse;
import com.RedisRabbitMQ.AuthApplication.DTO.RegisterRequest;
import com.RedisRabbitMQ.AuthApplication.DTO.UserResponse;
import com.RedisRabbitMQ.AuthApplication.Entiry.User;
import com.RedisRabbitMQ.AuthApplication.Exception.UserEmailNotFoundException;
import com.RedisRabbitMQ.AuthApplication.Repository.UserRepository;
import com.RedisRabbitMQ.AuthApplication.Security.CustomUserDetail;
import com.RedisRabbitMQ.AuthApplication.Security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder encoder;
	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;
	private final RedisService redisService;
	
	@Override
	public AuthResponse<LoginResponse> login(LoginRequest dto) {
		// TODO Auto-generated method stub
		
		Authentication authentication=authenticationManager.
				authenticate(
						new UsernamePasswordAuthenticationToken(
								dto.getEmail(),dto.getPassword()));
		
		CustomUserDetail customUserDetail=(CustomUserDetail) authentication.getPrincipal();
		User user=customUserDetail.getUser();
		
		String token = jwtUtil.generateToken(dto.getEmail());
		redisService.saveToken(dto.getEmail(), token);
		LoginResponse response=new LoginResponse(user.getEmail(), token);
				
		return new AuthResponse<>(
				true,
				"Login Successfully",
				response);
	}
	
	public AuthResponse<UserResponse>register(RegisterRequest dto){
		
		if(userRepository.findByEmail(dto.getEmail()).isPresent()) {
			new RuntimeException("Email Already Exists...!");
		}
		
		User user=new User();
		user.setEmail(dto.getEmail());
		user.setPassword(encoder.encode(dto.getPassword()));
		user.setUsername(dto.getUsername());
		user.setDepartment(dto.getDepartment());
		
		User savedUser=userRepository.save(user);
		
		UserResponse response=new UserResponse(
				savedUser.getId(), 
				savedUser.getEmail(),
				savedUser.getUsername(),
				savedUser.getDepartment());
		
		return new AuthResponse<>(
				true, 
				"User Register successfully", 
				response);
	}

	@Cacheable(value = "getAllUsers")
	@Override
	public AuthResponse<List<UserResponse>> getAllUser() {
		// TODO Auto-generated method stub
		
		List<UserResponse>users=userRepository.findAll()
				.stream()
				.map(user->new UserResponse(
						user.getId(), 
						user.getEmail(), 
						user.getUsername(),
						user.getDepartment())
						)
				.collect(Collectors.toList());
		return new AuthResponse<List<UserResponse>>(
				true, 
				"All Users Fetch Successfully",
				users);
	}

	@Cacheable(value = "users", key = "#email")
	@Override
	public AuthResponse<UserResponse> getUserByEmail(String email) {
		// TODO Auto-generated method stub
		User user=userRepository.findByEmail(email).
				orElseThrow(()-> 
				new UserEmailNotFoundException("Email not Found"));
		UserResponse response=new UserResponse(
				user.getId(),
				user.getEmail(),
				user.getUsername(),
				user.getDepartment());
		
		return new AuthResponse<>(
				true,
				"User "+email+ " Fetch successfully",
				response);
	}

	@Caching(put = {
			@CachePut(value = "users", key="#email")
	}
	, evict = {
			@CacheEvict(value = "getAllUsers",allEntries = true)
	})
	@Override
	public AuthResponse<UserResponse> updateUser(RegisterRequest dto, String email) {
		// TODO Auto-generated method stub
		User user=userRepository.findByEmail(email).
				orElseThrow(()->
				new UserEmailNotFoundException("Email Not Found"));
		
		user.setEmail(dto.getEmail());
		if(dto.getPassword()!=null) {
		user.setPassword(encoder.encode(dto.getPassword()));
		}
		user.setUsername(dto.getUsername());
		user.setDepartment(dto.getDepartment());
		
		User updateUser=userRepository.save(user);
		
		UserResponse response=new UserResponse(
				updateUser.getId(),
				updateUser.getEmail(),
				updateUser.getUsername(),
				updateUser.getDepartment());
		
		return new  AuthResponse<>(
				true,
				"User "+email+ " Updated Successfully...",
				response);
	}
	@Caching(evict = {
			@CacheEvict(value = "users", key = "#email",beforeInvocation = true),
			@CacheEvict(value = "getAllUsers",allEntries = true)
	})

	@Override
	public AuthResponse<UserResponse> deleteUserByEmail(String email) {
		// TODO Auto-generated method stub
		User user=userRepository.findByEmail(email).orElseThrow(()->
		new UserEmailNotFoundException("Email Not Found"));
		
		userRepository.delete(user);
		
		return new AuthResponse<>(
				true,
				"User "+ email+" Deleted Successfully...",
				null);
	}
	
	

}

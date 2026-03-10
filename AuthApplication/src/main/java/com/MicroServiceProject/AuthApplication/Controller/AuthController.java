package com.MicroServiceProject.AuthApplication.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MicroServiceProject.AuthApplication.DTO.AuthResponse;
import com.MicroServiceProject.AuthApplication.DTO.LoginRequest;
import com.MicroServiceProject.AuthApplication.DTO.LoginResponse;
import com.MicroServiceProject.AuthApplication.DTO.RegisterRequest;
import com.MicroServiceProject.AuthApplication.DTO.UserResponse;
import com.MicroServiceProject.AuthApplication.Service.RedisService;
import com.MicroServiceProject.AuthApplication.Service.UserServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

	private final UserServiceImpl serviceImpl;
	private final RedisService redisService;
	@PostMapping("/login")
	public ResponseEntity<AuthResponse<LoginResponse>>login(@Valid @RequestBody LoginRequest dto){
		
		return ResponseEntity.ok(serviceImpl.login(dto));
	}
	@GetMapping("/test")
	public String testApi(){
	    return "Jenkins pipeline working!";
	}
	
	@PostMapping("/register")
	public ResponseEntity<AuthResponse<UserResponse>>registerUser(@Valid @RequestBody RegisterRequest request){
		
		return ResponseEntity.ok(serviceImpl.register(request));
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> logout(Authentication authentication){

	    String email = authentication.getName();

	    redisService.deleteToken(email);

	    return ResponseEntity.ok("Logout successful");
	}
	//Get All Users 
	@GetMapping("/users")
	public ResponseEntity<AuthResponse<List<UserResponse>>>getAllUsers(){
		
		return ResponseEntity.ok(serviceImpl.getAllUser());
		
	}
	
	//Get Users by Email
	@GetMapping("/users/{email}")
	public ResponseEntity<AuthResponse<UserResponse>>getUserByEmail(@PathVariable String email){
		
		return ResponseEntity.ok(serviceImpl.getUserByEmail(email));
	}
	
	//
	@PutMapping("/update/{email}")
	public ResponseEntity<AuthResponse<UserResponse>>updateUserByEmail(
			@Valid @RequestBody RegisterRequest dto, @PathVariable String email){
		
		return ResponseEntity.ok(serviceImpl.updateUser(dto, email));
	}
	
	@DeleteMapping("/delete/{email}")
	public ResponseEntity<AuthResponse<UserResponse>>deleteUserByEmail(@PathVariable String email){
		return ResponseEntity.ok(serviceImpl.deleteUserByEmail(email));
	}
}

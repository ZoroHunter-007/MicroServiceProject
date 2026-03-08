package com.RedisRabbitMQ.AuthApplication.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

	@Email
	@NotBlank
	private String email;
	
	@Size(min=6, message = "Password at least 6 Characters")
	@NotBlank
	private String password;
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String department;
}

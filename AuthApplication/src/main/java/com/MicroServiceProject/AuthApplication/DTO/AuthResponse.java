package com.MicroServiceProject.AuthApplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse<T> {

	private boolean status;
	private String message;
	private T data;
	
}

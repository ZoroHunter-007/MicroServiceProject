package com.MicroServiceProject.AuthApplication.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.MicroServiceProject.AuthApplication.DTO.AuthResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserEmailNotFoundException.class)
	public ResponseEntity<AuthResponse<Object>>handelUserEmailNotFoundException(UserEmailNotFoundException ex){
		
		AuthResponse<Object>response=new AuthResponse<Object>(
				false, 
				"Email and Password incorrect",
				null);
		
		return new  ResponseEntity<>(
				response,HttpStatus.NOT_FOUND);
	}
}

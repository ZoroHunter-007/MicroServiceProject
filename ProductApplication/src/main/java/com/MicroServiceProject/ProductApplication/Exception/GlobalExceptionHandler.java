package com.MicroServiceProject.ProductApplication.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.MicroServiceProject.ProductApplication.Response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<Object>>handelResourceNotFound(ResourceNotFoundException ex){
		
		ApiResponse<Object>response=new ApiResponse<Object>(
				false,
				"Product Not Found",
				null);
		
		return new ResponseEntity<>(
				response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ApiResponse<Object>>handelBadRequestException(BadRequestException ex){
		
		ApiResponse<Object>response=new ApiResponse<Object>(
				false, 
				"Invalid Details enter",
				null);
		
		return new ResponseEntity<>(
				response,HttpStatus.BAD_REQUEST);
	}
}

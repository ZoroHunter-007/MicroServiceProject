package com.MicroServiceProject.OrderService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.MicroServiceProject.OrderService.Response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(OrderNotFoundExcetion.class)
	public ResponseEntity<ApiResponse<Object>>handelOrderNotFound(OrderNotFoundExcetion ex){
		
		ApiResponse<Object>response=new ApiResponse<Object>(
				false, 
				"Order Not Found", 
				null);
		return new ResponseEntity<ApiResponse<Object>>(
				response,HttpStatus.NOT_FOUND);
	}
}

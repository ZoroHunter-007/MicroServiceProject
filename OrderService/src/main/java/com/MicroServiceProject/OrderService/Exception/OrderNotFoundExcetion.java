package com.MicroServiceProject.OrderService.Exception;

public class OrderNotFoundExcetion extends RuntimeException{

	public OrderNotFoundExcetion(String message) {
		super(message);
	}
}

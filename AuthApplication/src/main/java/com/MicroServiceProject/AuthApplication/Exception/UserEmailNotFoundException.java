package com.MicroServiceProject.AuthApplication.Exception;

public class UserEmailNotFoundException extends RuntimeException{

	public UserEmailNotFoundException(String message) {
		super(message);
	}
}

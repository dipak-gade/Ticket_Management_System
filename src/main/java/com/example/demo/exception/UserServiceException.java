package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class UserServiceException extends RuntimeException {

	private final HttpStatus httpStatus;

	public UserServiceException(HttpStatus httpStatus, String message) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}

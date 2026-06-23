package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class TicketServiceException extends RuntimeException {

	private final HttpStatus httpStatus;

	public TicketServiceException(String msg, HttpStatus httpStatus) {

		super(msg);
		this.httpStatus = httpStatus;

	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}

package com.example.demo.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.exception.TicketServiceException;
import com.example.demo.exception.UserServiceException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserServiceException.class)
	public ResponseEntity<String> handleUserServiceException(UserServiceException ex) {
		return new ResponseEntity<String>(ex.getMessage(), ex.getHttpStatus());
	}

	@ExceptionHandler(TicketServiceException.class)
	public ResponseEntity<String> handleTicketServiceException(TicketServiceException ex) {
		return new ResponseEntity<String>(ex.getMessage(), ex.getHttpStatus());
	}
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {

        return new ResponseEntity<String>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


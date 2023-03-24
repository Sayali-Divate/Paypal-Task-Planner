package com.taskplanner.demo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetails> noHandlerFoundExceptionHandler(NoHandlerFoundException ee, WebRequest req){
		
		ErrorDetails error = new ErrorDetails();
		error.setMessage(ee.getMessage());
		error.setDescription(req.getDescription(false));
		error.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> methodArgumentNotValidException(MethodArgumentNotValidException ee){
		
		ErrorDetails error = new ErrorDetails();
		error.setMessage(ee.getMessage());
		error.setDescription(ee.getBindingResult().getFieldError().getDefaultMessage());
		error.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(SprintNotFoundException.class)
	public ResponseEntity<ErrorDetails> sprintNotFoundExceptionHandler(SprintNotFoundException ee, WebRequest req){
		
		ErrorDetails error = new ErrorDetails();
		error.setMessage(ee.getMessage());
		error.setDescription(req.getDescription(false));
		error.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(TaskNotFoundException.class)
	public ResponseEntity<ErrorDetails> taskNotFoundExceptionHandler(TaskNotFoundException ee, WebRequest req){
		
		ErrorDetails error = new ErrorDetails();
		error.setMessage(ee.getMessage());
		error.setDescription(req.getDescription(false));
		error.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetails> userExceptionHandler(UserException ee, WebRequest req){
		
		ErrorDetails error = new ErrorDetails();
		error.setMessage(ee.getMessage());
		error.setDescription(req.getDescription(false));
		error.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> exceptionHandler(Exception ee, WebRequest req){
		
		ErrorDetails error = new ErrorDetails();
		error.setMessage(ee.getMessage());
		error.setDescription(req.getDescription(false));
		error.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	

}

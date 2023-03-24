package com.taskplanner.demo.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorDetails {
	
	private String message;
	private String description;
	private LocalDateTime timeStamp;

}

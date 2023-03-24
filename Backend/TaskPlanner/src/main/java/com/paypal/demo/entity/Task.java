package com.paypal.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class Task {

	private Long id;
	private String title;
	private String description;
	private TaskType taskType;
	private TaskStatus taskStatus;		
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="assignee_id")
	private User assignee;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="sprint_id")
	private Sprint sprint;
	
	
}

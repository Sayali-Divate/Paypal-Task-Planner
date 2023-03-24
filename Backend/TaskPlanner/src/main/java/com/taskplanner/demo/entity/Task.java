package com.taskplanner.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String description;
	
	@Enumerated(EnumType.STRING)
	private TaskType taskType;
	
	@Enumerated(EnumType.STRING)
	private TaskStatus taskStatus;		
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="assignee_id")
	private User assignee;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="sprint_id")
	private Sprint sprint;
	
	
}

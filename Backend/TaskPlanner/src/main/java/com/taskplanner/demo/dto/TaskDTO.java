package com.taskplanner.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskDTO {

	@NotNull
	private String title;
	private String description;	
	private String taskType;
	private String assignee;
}

package com.taskplanner.demo.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SprintDTO {
	
	@NotNull
	private String name;
	
	private String description;
	private LocalDate startDate;
	private LocalDate deadLine;

}

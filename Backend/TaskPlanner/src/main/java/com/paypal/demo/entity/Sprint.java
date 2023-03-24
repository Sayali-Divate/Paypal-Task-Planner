package com.paypal.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Sprint {

	private Long id;
	private String name;
	private String description;
	private LocalDate startDate;
	
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "sprint")
	private List<Task> tasksInSprint = new ArrayList<>();
}

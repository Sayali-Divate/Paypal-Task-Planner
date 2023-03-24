package com.paypal.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Sprint {

	private Long id;
	private String name;
	private String description;
	private LocalDate startDate;
}

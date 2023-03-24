package com.taskplanner.demo.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;	
	
	private String userName;
	
	@Column(unique = true)
	private String userEmail;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Sprint> sprints = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "assignee")
	private List<Task> tasks = new ArrayList<>();
	
	
}

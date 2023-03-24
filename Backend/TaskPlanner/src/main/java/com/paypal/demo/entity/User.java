package com.paypal.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
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
	private String userEmail;		
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Sprint> userSprints = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "assignee")
	private List<Task> userTasks = new ArrayList<>();
	
	
}

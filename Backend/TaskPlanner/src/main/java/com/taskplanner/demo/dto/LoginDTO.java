package com.taskplanner.demo.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class LoginDTO {

	@Email
	private String email;
	private String password;
}

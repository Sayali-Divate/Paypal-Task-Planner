package com.taskplanner.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskplanner.demo.dto.LoginDTO;
import com.taskplanner.demo.dto.UserDTO;
import com.taskplanner.demo.entity.User;
import com.taskplanner.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {	
	
	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	
	
	/**
	 * an api for creating an account for a new user
	 * @param userInfo - It is a DTO which contains basic user information
	 * @return - this api returns a user which has got a userId after successfully 
	 * 			creating an account with a status code
	 */
	@PostMapping("/welcome/signup")
	public ResponseEntity<User> createAcount(@Valid @RequestBody UserDTO userInfo) {
		
//		hashing the user password
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		
		User user = userService.createAccount(userInfo);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	
	@PostMapping("/auth/login")
	public ResponseEntity<String> login(@Valid @RequestBody LoginDTO loginReq){
		
		Authentication authenticate = authenticationManager.authenticate(
				
				new UsernamePasswordAuthenticationToken(
						loginReq.getEmail(), 
						loginReq.getPassword())				
				);
		
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		
		return new ResponseEntity<>("Login Successfull", HttpStatus.ACCEPTED);
	} 

}

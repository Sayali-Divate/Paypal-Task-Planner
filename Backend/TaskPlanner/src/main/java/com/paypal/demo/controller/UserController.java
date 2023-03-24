package com.paypal.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.demo.dto.UserDTO;
import com.paypal.demo.entity.User;
import com.paypal.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {	
	
	@Autowired
	UserService userService;
	
	/**
	 * an api for creating an account for a new user
	 * @param userInfo - It is a DTO which contains basic user information
	 * @return - this api returns a user which has got a userId after successfully 
	 * 			creating an account with a status code
	 */
	@PostMapping("/welcome/signup")
	public ResponseEntity<User> createAcount(@Valid @RequestBody UserDTO userInfo) {
		
		User user = userService.createAccount(userInfo);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

}

package com.taskplanner.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.taskplanner.demo.dto.UserDTO;
import com.taskplanner.demo.entity.User;
import com.taskplanner.demo.exception.UserException;
import com.taskplanner.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User createAccount(UserDTO user) throws UserException {
		
//		checking if the user already exist or not in database
		
		Optional<User> us = userRepository.findByUserEmail(user.getEmail());
		
		if(us.isPresent()) throw new UserException("User has already signed up");
		
//		creating a User object..
		User userToBeSaved = new User();
		userToBeSaved.setUserName(user.getName());
		userToBeSaved.setUserEmail(user.getEmail());
		userToBeSaved.setPassword(user.getPassword());
		
//		saving the user into the database...
		return userRepository.save(userToBeSaved);	
		
	}

	@Override
	public User getLoggedInUser() throws UserException {
		
		UserDetails user  = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(user==null) throw new UserException("Please log in to continue");
		
		Optional<User> userOptional = userRepository.findByUserEmail(user.getUsername());		
		
		return userOptional.get();
	}

}

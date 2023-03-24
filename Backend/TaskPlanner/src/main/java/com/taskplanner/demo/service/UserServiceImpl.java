package com.taskplanner.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskplanner.demo.dto.UserDTO;
import com.taskplanner.demo.entity.User;
import com.taskplanner.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User createAccount(UserDTO user) {
		
//		creating a User object..
		User userToBeSaved = new User();
		userToBeSaved.setUserName(user.getName());
		userToBeSaved.setUserEmail(user.getEmail());
		userToBeSaved.setPassword(user.getPassword());
		
//		saving the user into the database...
		return userRepository.save(userToBeSaved);	
		
	}

}

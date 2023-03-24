package com.paypal.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.demo.dto.UserDTO;
import com.paypal.demo.entity.User;
import com.paypal.demo.repository.UserRepository;

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

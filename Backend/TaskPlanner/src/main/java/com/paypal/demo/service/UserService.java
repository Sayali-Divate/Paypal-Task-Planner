package com.paypal.demo.service;

import com.paypal.demo.dto.UserDTO;
import com.paypal.demo.entity.User;

public interface UserService {
	
	/**
	 * This method creates an account for a new user
	 * @param user - It takes UserDTO as an object
	 * @return - Returns a user which has got a userId
	 */
	User createAccount(UserDTO user);

}

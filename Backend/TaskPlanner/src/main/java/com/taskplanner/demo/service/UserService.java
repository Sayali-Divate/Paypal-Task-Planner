package com.taskplanner.demo.service;

import com.taskplanner.demo.dto.UserDTO;
import com.taskplanner.demo.entity.User;
import com.taskplanner.demo.exception.UserException;

public interface UserService {
	
	/**
	 * This method creates an account for a new user
	 * @param user - It takes UserDTO as an object
	 * @return - Returns a user which has got a userId
	 * @throws UserException 
	 */
	User createAccount(UserDTO user) throws UserException;
	
	
	/**
	 * this is for getting the User object who has currently logged in into the application
	 * @return  User Object
	 * @throws UserException - thrown when user is not logged in
	 */
	User getLoggedInUser() throws UserException;

}

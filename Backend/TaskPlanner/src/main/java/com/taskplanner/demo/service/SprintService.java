package com.taskplanner.demo.service;

import java.util.List;

import com.taskplanner.demo.dto.SprintDTO;
import com.taskplanner.demo.entity.Sprint;
import com.taskplanner.demo.exception.UserException;

public interface SprintService {
	
	/**
	 * to create sprint and save into database
	 * @param sprint - here sprint is SprintDTO which contains name, description, start date and deadline date
	 * @return It returns a Sprint 
	 * @throws UserException 
	 */
	Sprint createSprint(SprintDTO sprint) throws UserException;
	
	
	/**
	 * to get all the sprints associated with the current logged in user
	 * @return returns the list of sprint
	 * @throws UserException 
	 */
	List<Sprint> getListOfSprint() throws UserException;
}

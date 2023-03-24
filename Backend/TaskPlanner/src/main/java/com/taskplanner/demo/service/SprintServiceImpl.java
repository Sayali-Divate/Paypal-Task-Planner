package com.taskplanner.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.taskplanner.demo.dto.SprintDTO;
import com.taskplanner.demo.entity.Sprint;
import com.taskplanner.demo.entity.User;
import com.taskplanner.demo.exception.UserException;
import com.taskplanner.demo.repository.SprintRepository;
import com.taskplanner.demo.repository.UserRepository;

@Service
public class SprintServiceImpl implements SprintService {
	
	@Autowired
	SprintRepository sprintRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public Sprint createSprint(SprintDTO sprint) throws UserException {
		
//		getting the currently logged in user
		User CurrentUser  =  userService.getLoggedInUser();		
		
		Sprint sprintToBeSaved = new Sprint();
		sprintToBeSaved.setName(sprint.getName());
		sprintToBeSaved.setDescription(sprint.getDescription());
		sprintToBeSaved.setStartDate(sprint.getStartDate());
		
//		new sprint created also needed to be added in the user		
		CurrentUser.getSprints().add(sprintToBeSaved);
		
		userRepository.save(CurrentUser);
		
		return sprintToBeSaved;
	}

	@Override
	public List<Sprint> getListOfSprint() throws UserException {
		
//		getting the currently logged in user
		User CurrentUser  =  userService.getLoggedInUser();	
		
		return CurrentUser.getSprints();
	}	
	

}

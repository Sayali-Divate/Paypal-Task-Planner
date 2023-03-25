package com.taskplanner.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskplanner.demo.dto.SprintDTO;
import com.taskplanner.demo.entity.Sprint;
import com.taskplanner.demo.exception.UserException;
import com.taskplanner.demo.service.SprintService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sprint")
public class SprintController {
	
	@Autowired
	SprintService sprintService;
	
	/**
	 * this controller is for creating a sprint
	 * @param sprintReq - It is a DTO of a sprint which contains all the neccessary information
	 * @return returns a sprint object with the id
	 * @throws UserException - thrown when person is not logged in
	 */

	@PostMapping("/sprint-creation")
	public ResponseEntity<Sprint> createSprint(@Valid @RequestBody SprintDTO sprintReq) throws UserException{
		
		Sprint sprint = sprintService.createSprint(sprintReq);
		return new ResponseEntity<>(sprint, HttpStatus.CREATED);
	}
	
	
	/**
	 * this gives the list of sprints of the logged in user
	 * @return - the list of sprints of the logged in user
	 * @throws UserException thrown when person is not logged in
	 */
	@GetMapping("/list")
	ResponseEntity<List<Sprint>> getSprintList() throws UserException{
		
		List<Sprint> sprintList = sprintService.getListOfSprint();
		return new ResponseEntity<>(sprintList, HttpStatus.ACCEPTED);
	}
	
}

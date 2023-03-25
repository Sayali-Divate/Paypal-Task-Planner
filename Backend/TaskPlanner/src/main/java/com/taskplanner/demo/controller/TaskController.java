package com.taskplanner.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskplanner.demo.dto.TaskDTO;
import com.taskplanner.demo.entity.Task;
import com.taskplanner.demo.exception.TaskNotFoundException;
import com.taskplanner.demo.exception.UserException;
import com.taskplanner.demo.service.TaskService;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	TaskService taskService;
	
	/**
	 * For creating a task
	 * @param taskReq - its a DTO with valid task Info
	 * @return new task with the id
	 * @throws UserException thrown when person is not logged in
	 */
	@PostMapping("/task-creation")
	public ResponseEntity<Task> createTask(@Valid @RequestBody TaskDTO taskReq) throws UserException{
		
		Task task = taskService.createTask(taskReq);
		return new ResponseEntity<>(task, HttpStatus.CREATED);
	}
	
	
	/**
	 * for changing the assignee of the task
	 * @param taskId - id of the task that needed to passed
	 * @param assigneeId - id of the new assignee that needed to be assigned to the task
	 * @return task with the changed assignee
	 * @throws UserException thrown when person is not logged in
	 * @throws TaskNotFoundException thrown when task id is not found
	 */
	@PostMapping("/task-assignee")
	public ResponseEntity<Task> changeTaskAssignee(@RequestParam("taskId") Long taskId, @RequestParam("assigneeId") Long assigneeId) throws UserException, TaskNotFoundException{
		
		Task task = taskService.changeAssignee(taskId, assigneeId);
		return new ResponseEntity<>(task, HttpStatus.ACCEPTED);
	}
	
	
	/**
	 * for changing the status of the task
	 * @param taskId - id of the task that needed to passed
	 * @param status - the status that needed to be set
	 * @return the task with the changed status
	 * @throws TaskNotFoundException thrown when task id is not found
	 */
	@PostMapping("/status-change/{taskId}")
	public ResponseEntity<Task> changeStatus(@PathParam("taskId") Long taskId, @RequestParam("status") String status) throws TaskNotFoundException{
		
		Task task = taskService.changeStatus(taskId, status);
		return new ResponseEntity<>(task, HttpStatus.ACCEPTED);
	}
	
	
}

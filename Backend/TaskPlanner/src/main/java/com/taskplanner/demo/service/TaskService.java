package com.taskplanner.demo.service;

import java.util.List;

import com.taskplanner.demo.dto.TaskDTO;
import com.taskplanner.demo.entity.Task;
import com.taskplanner.demo.exception.SprintNotFoundException;
import com.taskplanner.demo.exception.TaskNotFoundException;
import com.taskplanner.demo.exception.UserException;

public interface TaskService {

	/**
	 * For creating a task
	 * @param taskReq - its a DTO with valid task Info
	 * @return new task with the id
	 * @throws UserException thrown when person is not logged in
	 */
	Task createTask(TaskDTO task) throws UserException;
	
	
	/**
	 * for adding the task to the sprint
	 * @param sprintId - id of sprint to which task to be added
	 * @param taskId - id of the task which is to be added into the sprint
	 * @return task which is added to the sprint
	 * @throws TaskNotFoundException thrown when task id is not found
	 * @throws SprintNotFoundException thrown when sprint id is not found
	 */
	Task addTaskToSprint(Long sprintId, Long taskId) throws TaskNotFoundException, SprintNotFoundException;
	
	
	/**
	 * for changing the assignee of the task
	 * @param taskId - id of the task that needed to passed
	 * @param assigneeId - id of the new assignee that needed to be assigned to the task
	 * @return task with the changed assignee
	 * @throws UserException thrown when person is not logged in
	 * @throws TaskNotFoundException thrown when task id is not found
	 */
	Task changeAssignee(Long taskId, Long assigneeId) throws UserException, TaskNotFoundException;
	
	
	/**
	 * for changing the status of the task
	 * @param taskId - id of the task that needed to passed
	 * @param status - the status that needed to be set
	 * @return the task with the changed status
	 * @throws TaskNotFoundException thrown when task id is not found
	 */
	Task changeStatus(Long taskId, String status) throws TaskNotFoundException;
	
	
	/**
	 * for getting the list of task based on the assignee
	 * @param email - this is email of the assignee
	 * @return list of the tasks of particular assignee
	 * @throws UserException
	 */
	List<Task> getTasksByAssigneeId(String email) throws UserException;
}

package com.taskplanner.demo.service;

import java.util.List;

import com.taskplanner.demo.dto.TaskDTO;
import com.taskplanner.demo.entity.Task;
import com.taskplanner.demo.exception.SprintNotFoundException;
import com.taskplanner.demo.exception.TaskNotFoundException;
import com.taskplanner.demo.exception.UserException;

public interface TaskService {

	Task createTask(TaskDTO task) throws UserException;
	
	Task addTaskToSprint(Long sprintId, Long taskId) throws TaskNotFoundException, SprintNotFoundException;
	
	Task changeAssignee(Long taskId, Long assigneeId) throws UserException, TaskNotFoundException;
	
	Task changeStatus(Long taskId, String status) throws TaskNotFoundException;
	
	List<Task> getTasksByAssigneeId(String email) throws UserException;
}

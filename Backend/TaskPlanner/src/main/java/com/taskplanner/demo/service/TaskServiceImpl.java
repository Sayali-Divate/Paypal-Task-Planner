package com.taskplanner.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskplanner.demo.dto.TaskDTO;
import com.taskplanner.demo.entity.Sprint;
import com.taskplanner.demo.entity.Task;
import com.taskplanner.demo.entity.TaskStatus;
import com.taskplanner.demo.entity.TaskType;
import com.taskplanner.demo.entity.User;
import com.taskplanner.demo.exception.SprintNotFoundException;
import com.taskplanner.demo.exception.TaskNotFoundException;
import com.taskplanner.demo.exception.UserException;
import com.taskplanner.demo.repository.SprintRepository;
import com.taskplanner.demo.repository.TaskRepository;
import com.taskplanner.demo.repository.UserRepository;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	SprintRepository sprintRepository;

	@Override
	public Task createTask(TaskDTO task) throws UserException {

//		getting the current user
		User user = userService.getLoggedInUser();
		
//		creating an actual task
		Task taskToBeSaved = new Task();
		taskToBeSaved.setTitle(task.getTitle());
		taskToBeSaved.setDescription(task.getDescription());
		taskToBeSaved.setTaskStatus(TaskStatus.NO_STATUS);
		
		TaskType type = getTaskType(task.getTaskType());		
		taskToBeSaved.setTaskType(type);
		
//		find the assignee based on email entered
		Optional<User> assigneeOptional = userRepository.findByUserEmail(task.getAssignee());
		if(assigneeOptional.isEmpty()) throw new UserException("Assignee does not exist");
		
		taskToBeSaved.setAssignee(assigneeOptional.get());
		
		user.getTasks().add(taskToBeSaved);		

		userRepository.save(user);
		
		return taskRepository.save(taskToBeSaved);
	}

	public TaskType getTaskType(String taskType) {
		
		switch(taskType) {
		
			case "Bug" : return TaskType.BUG;
			case "Feature" : return TaskType.FEATURE;
			case "Story" : return TaskType.STROY;
			default : return null;
		}		
		
	}

	@Override
	public Task changeAssignee(Long taskId, Long assigneeId) throws UserException, TaskNotFoundException {
		
		Task task = taskRepository.findById(taskId).orElseThrow(()-> new TaskNotFoundException("Invalid Task"));
		
		User assignee = userRepository.findById(assigneeId).orElseThrow(()->new UserException("Assignee not found"));		
		
		
		task.setAssignee(assignee);
		return taskRepository.save(task);
		
	}

	@Override
	public Task changeStatus(Long taskId, String status) throws TaskNotFoundException {
		
		Task task = taskRepository.findById(taskId).orElseThrow(()-> new TaskNotFoundException("Invalid Task"));
		
		TaskStatus statusType = getStatusType(status);
		task.setTaskStatus(statusType);
		
		return taskRepository.save(task);
		
	}
	

	public TaskStatus getStatusType(String status) {
		
		switch(status) {
		
			case "To_Do" : return TaskStatus.TO_DO;
			case "In_Progress": return TaskStatus.IN_PROGRESS;
			case "Done" : return TaskStatus.DONE;
			default : return TaskStatus.NO_STATUS;
		}
		
	}

	@Override
	public List<Task> getTasksByAssigneeId(String email) throws UserException {
		
		User assignee = userRepository.findByUserEmail(email).orElseThrow(() -> new UserException("Assignee not found"));		
		return assignee.getTasks();
		
	}

	@Override
	public Task addTaskToSprint(Long sprintId, Long taskId) throws TaskNotFoundException, SprintNotFoundException {
		
		Task task = taskRepository.findById(taskId).orElseThrow(()-> new TaskNotFoundException("Invalid Task"));
		Sprint sprint = sprintRepository.findById(sprintId).orElseThrow(()-> new SprintNotFoundException("Invalid Sprint"));
		
		task.setSprint(sprint);
		return taskRepository.save(task);
	}

	
	
	

	
}

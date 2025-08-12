package com.tasks.TaskAPI.controller;

import com.tasks.TaskAPI.Task;
import com.tasks.TaskAPI.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@PostMapping
	public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
		Task createdTask = taskService.createTask(task);
		return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
	}

	// GET /tasks: Get all tasks with pagination
	@GetMapping
	public ResponseEntity<Page<Task>> getAllTasks(@PageableDefault(size = 10) Pageable pageable) {
		Page<Task> tasks = taskService.getAllTasks(pageable);
		return new ResponseEntity<>(tasks, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable String id) {
		Optional<Task> task = taskService.getTaskById(id);
		return task.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable String id, @Valid @RequestBody Task taskDetails) {
		Optional<Task> updatedTask = taskService.updateTask(id, taskDetails);
		return updatedTask.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable String id) {
		if (taskService.deleteTask(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}

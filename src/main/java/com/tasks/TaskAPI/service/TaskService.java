package com.tasks.TaskAPI.service;

import com.tasks.TaskAPI.Task;
import com.tasks.TaskAPI.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing tasks.
 * It contains the business logic and interacts with the TaskRepository.
 */
@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    /**
     * Creates a new task and saves it to the database.
     * This method automatically sets the 'createdAt' timestamp.
     *
     * @param task The task to be created.
     * @return The created task.
     */
    public Task createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    /**
     * Retrieves all tasks from the database with pagination.
     *
     * @param pageable Contains pagination information (page number, size, sort).
     * @return A Page of tasks.
     */
    public Page<Task> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    /**
     * Retrieves a single task by its ID.
     *
     * @param id The ID of the task to retrieve.
     * @return An Optional containing the task if found, or an empty Optional otherwise.
     */
    public Optional<Task> getTaskById(String id) {
        return taskRepository.findById(id);
    }

    /**
     * Updates an existing task with new details.
     *
     * @param id The ID of the task to update.
     * @param taskDetails The new task details.
     * @return An Optional containing the updated task if the task was found,
     * or an empty Optional otherwise.
     */
    public Optional<Task> updateTask(String id, Task taskDetails) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task existingTask = optionalTask.get();
            existingTask.setTitle(taskDetails.getTitle());
            existingTask.setDescription(taskDetails.getDescription());
            existingTask.setCompleted(taskDetails.isCompleted());
            return Optional.of(taskRepository.save(existingTask));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Deletes a task by its ID.
     *
     * @param id The ID of the task to delete.
     * @return True if the task was found and deleted, false otherwise.
     */
    public boolean deleteTask(String id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            taskRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

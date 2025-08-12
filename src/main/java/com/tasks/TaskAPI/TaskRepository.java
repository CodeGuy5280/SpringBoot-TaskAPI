package com.tasks.TaskAPI;

import com.tasks.TaskAPI.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// The MongoRepository interface provides all the basic CRUD methods
// automatically (e.g., save, findById, findAll, deleteById, etc.)
@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
}
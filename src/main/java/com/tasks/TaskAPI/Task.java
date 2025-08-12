package com.tasks.TaskAPI;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data // Provides getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Creates a no-argument constructor
@AllArgsConstructor // Creates a constructor with all arguments
@Document(collection = "tasks")
public class Task {
    @Id
    private String id;

    @NotBlank(message = "Title is a required field.")
    @Size(max = 100, message = "Title must not exceed 100 characters.")
    private String title;

    @Size(max = 500, message = "Description must not exceed 500 characters.")
    private String description;

    private boolean completed;

    private LocalDateTime createdAt;
}

# Postman API Tests
This guide provides a set of basic tests to verify the functionality of all endpoints in your Spring Boot Task API. These tests cover successful requests, edge cases, and error handling.

#### All tests should be directed to the base URL: http://localhost:8080/tasks

## 1. Test POST /tasks (Create a Task)

This test verifies that you can successfully create a new task.

#### A. Successful Creation

### Method: POST

URL: http://localhost:8080/tasks

Body: raw with JSON type.

{
"title": "Learn Spring Boot",
"description": "Master the fundamentals of Spring Boot API development.",
"completed": false
}

Expected Response:

Status: 201 Created

Body: The JSON response should contain the created task, including a generated id and the createdAt timestamp.

#### B. Validation Failure (Bad Request)

### Method: POST

URL: http://localhost:8080/tasks

Body: raw with JSON type. (This request has a blank title.)

{
"title": "",
"description": "This request should fail validation.",
"completed": false
}

Expected Response:

Status: 400 Bad Request

Body: The JSON response should contain a map of validation errors, indicating that the title field is required.

## 2. Test GET /tasks (Get All Tasks with Pagination)

This test verifies that you can retrieve a list of all tasks. You'll need to create a few tasks first to see the results.

### Method: GET

URL: http://localhost:8080/tasks

Parameters (optional): To test pagination, add parameters like page and size.

http://localhost:8080/tasks?page=0&size=5

Expected Response:

Status: 200 OK

Body: The response body should be a Page object containing a list of tasks. The object will also include metadata like totalElements, totalPages, and number.

## 3. Test GET /tasks/{id} (Get Task by ID)

This test verifies that you can retrieve a specific task. You will need to use an ID from a task you've already created.

A. Successful Retrieval

### Method: GET

URL: http://localhost:8080/tasks/{id} (e.g., http://localhost:8080/tasks/64c0d0c366a7b73c26b88825)

Expected Response:

Status: 200 OK

Body: The JSON response should contain the details of the single task with the specified ID.

B. Task Not Found

### Method: GET

URL: http://localhost:8080/tasks/{id} (e.g., http://localhost:8080/tasks/non_existent_id)

Expected Response:

Status: 404 Not Found

Body: The response body should be empty.

## 4. Test PUT /tasks/{id} (Update a Task)

This test verifies that you can update an existing task.

A. Successful Update

### Method: PUT

URL: http://localhost:8080/tasks/{id} (Use the ID of a created task.)

Body: raw with JSON type.

{
"title": "Learn Spring Boot (Completed)",
"description": "Mastered the fundamentals of Spring Boot API development.",
"completed": true
}

Expected Response:

Status: 200 OK

Body: The response body should contain the updated task with the new values.

B. Task Not Found

### Method: PUT

URL: http://localhost:8080/tasks/non_existent_id

Expected Response:

Status: 404 Not Found

Body: The response body should be empty.

## 5. Test DELETE /tasks/{id} (Delete a Task)

This test verifies that you can successfully delete a task.

A. Successful Deletion

### Method: DELETE

URL: http://localhost:8080/tasks/{id} (Use the ID of a created task.)

Expected Response:

Status: 204 No Content

Body: The response body should be empty.

B. Task Not Found

### Method: DELETE

URL: http://localhost:8080/tasks/non_existent_id

Expected Response:

Status: 404 Not Found

Body: The response body should be empty.


TaskAPI
This is a simple RESTful API for managing tasks. It is built with Spring Boot and uses MongoDB as its database. The API provides a full CRUD (Create, Read, Update, Delete) system for a Task resource.

Technologies Used

Spring Boot: The application framework for building the API.

MongoDB: The NoSQL database for data persistence.

Spring Data MongoDB: Simplifies database interactions.

Lombok: Reduces boilerplate code (getters, setters, constructors, etc.).

Maven: The project build and dependency management tool.

Prerequisites

Java 24+

Maven 3.8+

MongoDB Community Server installed and running.

Getting Started

Clone the repository:

git clone [your-repository-url]
cd TaskAPI

Start the MongoDB server:
Open a terminal and start the MongoDB server, specifying a local data path.

mongod --dbpath ~/data/db

Keep this terminal window open.

Run the Spring Boot application:
Open a new terminal and start the application using Maven.

./mvnw spring-boot:run

Alternatively, you can run the application directly from your IDE (e.g., by running the TaskApiApplication.java class).

API Endpoints

The API is accessible at http://localhost:8080/tasks.

Method

Endpoint

Description

POST

/tasks

Creates a new task.

GET

/tasks

Retrieves all tasks with pagination.

GET

/tasks/{id}

Retrieves a specific task by its unique ID.

PUT

/tasks/{id}

Updates an existing task by its ID.

DELETE

/tasks/{id}

Deletes a task by its ID.

Key Features

Request Validation: The API validates incoming Task data for required fields and length constraints, returning a 400 Bad Request for invalid input.

Pagination: The GET /tasks endpoint supports pagination. You can use query parameters like page and size to control the results.

Example: GET /tasks?page=0&size=10

Automatic Timestamp: The createdAt field is automatically populated when a new task is created.

Robust Error Handling: The application returns appropriate HTTP status codes and detailed error messages for various scenarios (e.g., 404 Not Found, 400 Bad Request).

Example Usage (cURL)

Create a new task:

curl -X POST \
http://localhost:8080/tasks \
-H 'Content-Type: application/json' \
-d '{
"title": "Build a new Spring Boot app",
"description": "Create a new project from scratch with all the dependencies.",
"completed": false
}'

Get all tasks (with default pagination):

curl http://localhost:8080/tasks


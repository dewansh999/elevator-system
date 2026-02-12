# Elevator Management System

A Spring Boot REST API that simulates a multi-elevator management system with scheduling, caching, database persistence, and Docker deployment.

---

## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Redis (Cloud Cache)
- Docker
- Maven
- Swagger / OpenAPI

---

## Features

- Request elevator
- Multi-elevator scheduling
- Elevator movement simulation
- Elevator logs with pagination
- Fault detection & repair
- Redis caching for elevator status
- PostgreSQL persistence
- Docker deployment ready
- Unit testing with JUnit

---

## Project Structure

controller
service
repository
entity
config
exception


---

## Running Locally

### 1. Start PostgreSQL
Make sure PostgreSQL is running.

Database:
elevatordb


---

### 2. Run application

```bash
mvn spring-boot:run
App runs on:

http://localhost:8080
Docker Run
Build image:

docker build -t elevator-system .
Run container:

docker run -p 8080:8080 elevator-system
API Endpoints
Method	Endpoint	Description
POST	/api/elevators/request	Request elevator
GET	/api/elevators/status	Get elevator status
POST	/api/elevators/simulate	Simulate movement
GET	/api/elevators/logs	Get logs
PUT	/api/elevators/{id}/assign	Manual assign
PUT	/api/elevators/{id}/repair	Repair elevator
Swagger
http://localhost:8080/swagger-ui.html
Author
Dewansh Gupta


---

Now commit it:

```bash
git add README.md
git commit -m "Added README"
git push

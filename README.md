![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.x-brightgreen)
![Docker](https://img.shields.io/badge/Docker-Enabled-blue)
![Redis](https://img.shields.io/badge/Redis-Caching-red)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![Build](https://img.shields.io/badge/Build-Maven-orange)

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

```
src/main/java/com/elevator/elevatorsystem
│
├── controller
├── service
├── repository
├── entity
├── config
└── exception
```

---
## Architecture

```
Client (Postman / Browser)
        |
        v
Spring Boot Application
        |
  ----------------------
  |                    |
PostgreSQL           Redis
(Database)           (Cache)
```
## System Flow

```
User Request
    |
Controller Layer
    |
Service Layer
    |
Repository Layer
    |
Database (PostgreSQL)

Cache Layer (Redis)
```


## Running Locally

### 1. Start PostgreSQL

Make sure PostgreSQL is running and create the database:

```
elevatordb
```

---

### 2. Run Application

```bash
mvn spring-boot:run
```

Application runs on:

```
http://localhost:8080
```

---

## Docker Run

### Build Image

```bash
docker build -t elevator-system .
```

### Run Container

```bash
docker run -p 8080:8080 elevator-system
```

---
## Postman Collection
Included in:
```
/postman/Elevator-System.postman_collection.json
```

---
## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/elevators/request | Request elevator |
| GET | /api/elevators/status | Get elevator status |
| POST | /api/elevators/simulate | Simulate movement |
| GET | /api/elevators/logs | Get logs |
| PUT | /api/elevators/{id}/assign | Manual assign elevator |
| PUT | /api/elevators/{id}/repair | Repair elevator |

---

## Swagger UI

http://localhost:8080/swagger-ui.html

---

## Author

Dewansh Gupta

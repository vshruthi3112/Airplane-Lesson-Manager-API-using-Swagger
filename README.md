# ✈️ Airplane Lesson Manager API

> **Week 2 Deliverable** — Spring Boot Basics: Controllers, DI, Service Layer, Repository Pattern, REST APIs with Swagger

## What This Project Teaches

| Concept               | Where to Look                          |
|-----------------------|----------------------------------------|
| REST Controller       | `controller/LessonController.java`     |
| Dependency Injection  | Constructor param in Controller        |
| Service Layer         | `service/LessonService.java`           |
| Swagger/OpenAPI       | `config/OpenApiConfig.java`            |
| Model/POJO            | `model/Lesson.java`                    |

## Quick Start

### Prerequisites
- Java 17+ installed
- Maven (or use the included wrapper)

### Run the Application

```bash
# Using Maven wrapper (recommended)
./mvnw spring-boot:run

# Or if you have Maven installed globally
mvn spring-boot:run
```

### Access the API

| URL                                          | What it does                  |
|----------------------------------------------|-------------------------------|
| http://localhost:9090/swagger-ui.html         | 🎯 Interactive Swagger UI     |
| http://localhost:9090/lessons                 | GET all lessons (JSON)        |
| http://localhost:9090/v3/api-docs             | Raw OpenAPI spec              |

## API Endpoints

```
GET    /lessons       → Get all lessons
GET    /lessons/{id}  → Get one lesson by ID
POST   /lessons       → Create a new lesson
PUT    /lessons/{id}  → Update an existing lesson
DELETE /lessons/{id}  → Delete a lesson
```

## Test with curl

```bash
# Get all lessons
curl http://localhost:9090/lessons

# Get lesson with ID 1
curl http://localhost:9090/lessons/1

# Create a new lesson
curl -X POST http://localhost:9090/lessons \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Engine Start Procedure",
    "status": "Unpublished",
    "author": "Your Name",
    "model": "737-8",
    "ataNumber": "72-00"
  }'

# Update lesson with ID 1
curl -X PUT http://localhost:9090/lessons/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Updated Title",
    "status": "Published",
    "author": "John Doe",
    "model": "737-8",
    "ataNumber": "00-00"
  }'

# Delete lesson with ID 2
curl -X DELETE http://localhost:9090/lessons/2
```

## Project Structure

```
src/main/java/com/example/lessonmanager/
├── AirplaneLessonManagerApplication.java   ← Entry point
├── config/
│   └── OpenApiConfig.java                  ← Swagger configuration
├── controller/
│   └── LessonController.java              ← REST endpoints (web layer)
├── model/
│   └── Lesson.java                        ← Data model (POJO)
└── service/
    └── LessonService.java                 ← Business logic + in-memory data
```

## Architecture Diagram

```
┌──────────────────────────────────────────────────────────┐
│                    HTTP Client                            │
│          (Browser / Swagger UI / curl / Postman)         │
└────────────────────────────┬─────────────────────────────┘
                             │ HTTP Request
                             ▼
┌──────────────────────────────────────────────────────────┐
│  CONTROLLER LAYER (@RestController)                      │
│  • Handles HTTP methods (GET, POST, PUT, DELETE)         │
│  • Extracts path params, request bodies                  │
│  • Returns ResponseEntity with status codes              │
│  • DOES NOT contain business logic                       │
└────────────────────────────┬─────────────────────────────┘
                             │ Method call
                             ▼
┌──────────────────────────────────────────────────────────┐
│  SERVICE LAYER (@Service)                                │
│  • Contains business logic and validation                │
│  • Orchestrates operations                               │
│  • Sets defaults (status, dateModified)                  │
│  • DOES NOT handle HTTP or data storage directly         │
└────────────────────────────┬─────────────────────────────┘
                             │ Method call
                             ▼
┌──────────────────────────────────────────────────────────┐
│  REPOSITORY LAYER (@Repository)                          │
│  • Handles data access (CRUD on the data store)          │
│  • Currently: HashMap (in-memory)                        │
│  • Later: JPA + Database                                 │
│  • DOES NOT contain business logic                       │
└──────────────────────────────────────────────────────────┘
```

## Key Takeaways

1. **Separation of Concerns** — Each layer has ONE job
2. **Dependency Injection** — Spring wires everything together via constructors
3. **ResponseEntity** — Gives full control over HTTP responses
4. **Optional** — Handles "not found" without null pointer exceptions
5. **Swagger** — Free interactive documentation from annotations

## What's Next (Week 3+)

- Add Spring Data JPA + H2 database
- Add validation (`@Valid`, `@NotBlank`)
- Add exception handling (`@ControllerAdvice`)
- Add pagination and filtering

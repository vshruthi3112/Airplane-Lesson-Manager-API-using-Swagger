---
inclusion: auto
---

# Project Guide

This is a Spring Boot REST API for managing airplane training lessons (CRUD). It's a learning project.

## Architecture

Controller → Service → Repository → HashMap (in-memory)

## How to Run

```bash
./mvnw spring-boot:run
```

Swagger UI: http://localhost:8080/swagger-ui.html

## Rules

- Use constructor injection (not @Autowired on fields)
- Keep controllers thin — logic goes in Service
- Return ResponseEntity for explicit HTTP status codes
- Add a doc comment on each class

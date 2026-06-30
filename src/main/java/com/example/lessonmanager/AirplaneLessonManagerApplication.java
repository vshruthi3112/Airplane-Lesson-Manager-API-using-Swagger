package com.example.lessonmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ============================================================
 * AIRPLANE LESSON MANAGER - Main Application Entry Point
 * ============================================================
 *
 * This is where Spring Boot starts. The @SpringBootApplication annotation
 * does THREE things at once:
 *
 * 1. @Configuration    - Marks this class as a source of bean definitions
 * 2. @EnableAutoConfiguration - Tells Spring Boot to auto-configure based on dependencies
 * 3. @ComponentScan    - Scans this package and sub-packages for @Component, @Service, @Controller, etc.
 *
 * WHAT HAPPENS WHEN YOU RUN THIS:
 * - Spring Boot starts an embedded Tomcat server (port 8080 by default)
 * - It scans all packages under com.example.lessonmanager for annotated classes
 * - It wires up all dependencies (Dependency Injection)
 * - Your REST API becomes available at http://localhost:8080
 * - Swagger UI is available at http://localhost:8080/swagger-ui.html
 */
@SpringBootApplication
public class AirplaneLessonManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirplaneLessonManagerApplication.class, args);
    }
}

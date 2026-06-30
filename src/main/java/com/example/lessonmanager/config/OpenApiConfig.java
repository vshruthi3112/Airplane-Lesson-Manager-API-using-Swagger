package com.example.lessonmanager.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ============================================================
 * SWAGGER / OPENAPI CONFIGURATION
 * ============================================================
 *
 * This class customizes the Swagger UI page with our API's details.
 *
 * WHAT IS SWAGGER?
 * -----------------
 * Swagger (now called OpenAPI) is a tool that:
 * 1. Auto-generates documentation from your code
 * 2. Provides an interactive UI to test your API
 * 3. Shows request/response schemas
 *
 * After starting the app, visit: http://localhost:8080/swagger-ui.html
 *
 * WHAT IS @Configuration?
 * ------------------------
 * This tells Spring: "This class defines beans (objects) that should be
 * created and managed by the Spring container."
 *
 * WHAT IS @Bean?
 * ---------------
 * A method annotated with @Bean returns an object that Spring should manage.
 * Think of it as: "Hey Spring, here's an object I want you to keep track of
 * and inject wherever it's needed."
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("✈️ Airplane Lesson Manager API")
                        .version("1.0.0")
                        .description("""
                                REST API for managing airplane training lessons.
                                
                                **Week 2 Learning Deliverable** — This project demonstrates:
                                - Spring Boot REST Controllers
                                - Dependency Injection (Constructor-based)
                                - Service Layer pattern
                                - Repository Pattern (in-memory)
                                - Swagger/OpenAPI documentation
                                
                                **Architecture Flow:**
                                ```
                                HTTP Request → Controller → Service → Repository → In-Memory Store
                                ```
                                
                                **No database is used** — data is stored in a HashMap and resets on restart.
                                """)
                        .contact(new Contact()
                                .name("Backend Learner")
                                .email("learner@example.com")));
    }
}

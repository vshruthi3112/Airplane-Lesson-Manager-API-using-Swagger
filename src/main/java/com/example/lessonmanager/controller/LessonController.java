package com.example.lessonmanager.controller;

import com.example.lessonmanager.model.Lesson;
import com.example.lessonmanager.service.LessonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ============================================================
 * LESSON CONTROLLER (Presentation / Web Layer)
 * ============================================================
 *
 * WHAT IS A CONTROLLER?
 * ----------------------
 * A Controller is the entry point for HTTP requests. It:
 * 1. Receives the HTTP request (GET, POST, PUT, DELETE)
 * 2. Extracts data from the request (path params, body, query params)
 * 3. Calls the Service layer to do the work
 * 4. Returns an HTTP response (status code + body)
 *
 * KEY ANNOTATIONS EXPLAINED:
 * ---------------------------
 * @RestController = @Controller + @ResponseBody
 *   - @Controller: Marks this as a web controller (handles HTTP requests)
 *   - @ResponseBody: Automatically converts return values to JSON
 *
 * @RequestMapping("/lessons"): All endpoints in this controller start with /lessons
 *
 * @GetMapping, @PostMapping, etc.: Map HTTP methods to Java methods
 *
 * DEPENDENCY INJECTION:
 * ---------------------
 * The LessonService is injected via the constructor — same pattern as the Service layer.
 * Controller → Service → Repository (each layer only talks to the one below it)
 *
 * SWAGGER ANNOTATIONS:
 * --------------------
 * @Tag, @Operation, @ApiResponse: These generate the interactive Swagger UI documentation.
 * They don't change behavior — they just describe your API for developers.
 */
@RestController
@RequestMapping("/lessons")
@Tag(name = "Lessons", description = "Airplane Lesson Management API - CRUD operations for training lessons")
public class LessonController {

    private final LessonService lessonService;

    /**
     * Constructor Injection — Spring provides the LessonService automatically.
     */
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    // =====================================================================
    // GET /lessons — Retrieve all lessons
    // =====================================================================
    @GetMapping
    @Operation(
        summary = "Get all lessons",
        description = "Retrieves a list of all airplane training lessons. " +
                      "Returns an empty list if no lessons exist."
    )
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all lessons")
    public ResponseEntity<List<Lesson>> getAllLessons() {
        List<Lesson> lessons = lessonService.getAllLessons();
        return ResponseEntity.ok(lessons);
    }

    // =====================================================================
    // GET /lessons/{id} — Retrieve a single lesson by ID
    // =====================================================================
    @GetMapping("/{id}")
    @Operation(
        summary = "Get a lesson by ID",
        description = "Retrieves a single lesson by its unique identifier."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lesson found"),
        @ApiResponse(responseCode = "404", description = "Lesson not found with the given ID")
    })
    public ResponseEntity<Lesson> getLessonById(
            @Parameter(description = "ID of the lesson to retrieve", example = "1")
            @PathVariable Long id) {
        return lessonService.getLessonById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // =====================================================================
    // POST /lessons — Create a new lesson
    // =====================================================================
    @PostMapping
    @Operation(
        summary = "Create a new lesson",
        description = "Creates a new airplane training lesson. The ID is auto-generated. " +
                      "If status is not provided, it defaults to 'Unpublished'. " +
                      "If dateModified is not provided, it defaults to today's date."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Lesson created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid lesson data")
    })
    public ResponseEntity<Lesson> createLesson(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Lesson object to create (id field is ignored)")
            @RequestBody Lesson lesson) {
        Lesson createdLesson = lessonService.createLesson(lesson);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLesson);
    }

    // =====================================================================
    // PUT /lessons/{id} — Update an existing lesson
    // =====================================================================
    @PutMapping("/{id}")
    @Operation(
        summary = "Update an existing lesson",
        description = "Updates all fields of an existing lesson. The dateModified is " +
                      "automatically set to today's date."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lesson updated successfully"),
        @ApiResponse(responseCode = "404", description = "Lesson not found with the given ID")
    })
    public ResponseEntity<Lesson> updateLesson(
            @Parameter(description = "ID of the lesson to update", example = "1")
            @PathVariable Long id,
            @RequestBody Lesson lesson) {
        return lessonService.updateLesson(id, lesson)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // =====================================================================
    // DELETE /lessons/{id} — Delete a lesson
    // =====================================================================
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete a lesson",
        description = "Permanently removes a lesson by its ID. This action cannot be undone."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Lesson deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Lesson not found with the given ID")
    })
    public ResponseEntity<Void> deleteLesson(
            @Parameter(description = "ID of the lesson to delete", example = "1")
            @PathVariable Long id) {
        if (lessonService.deleteLesson(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

package com.example.lessonmanager.service;

import com.example.lessonmanager.model.Lesson;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ============================================================
 * LESSON SERVICE (Business Logic + In-Memory Data Storage)
 * ============================================================
 *
 * WHAT IS THE SERVICE LAYER?
 * ---------------------------
 * The Service layer contains your BUSINESS LOGIC — the rules and operations
 * that aren't just "return JSON". Examples:
 * - Validation rules
 * - Calculations
 * - Default values
 *
 * In this simplified version, the Service also stores data directly in a
 * HashMap (no separate Repository class). This keeps things simple for learning.
 * Later, you can extract data access into a Repository or add JPA.
 *
 * WHY @Service?
 * -------------
 * - Marks this as a Spring-managed bean (Spring creates and manages ONE instance)
 * - Semantically indicates this is a business logic component
 * - Allows Spring to inject it into Controllers via Constructor Injection
 *
 * DEPENDENCY INJECTION:
 * ---------------------
 * The Controller depends on this Service. Spring automatically provides
 * this Service instance to the Controller's constructor. You never call
 * "new LessonService()" yourself — Spring handles it.
 */
@Service
public class LessonService {

    // In-memory storage — simulates a database table
    // Key = lesson ID, Value = Lesson object
    // Data disappears when the app restarts (no database yet)
    private final Map<Long, Lesson> lessonStore = new HashMap<>();

    // Thread-safe counter to generate unique IDs (like an auto-increment column in a DB)
    private final AtomicLong idCounter = new AtomicLong(1);

    /**
     * Constructor — pre-populates with sample data so the API isn't empty.
     * This matches the lesson data from the screenshot.
     */
    public LessonService() {
        save(new Lesson(null, "INTRODUCTION: Tutorial - HUD Fault Test",
                "2026/06/29", "Unpublished", "John Doe", "737-8", "00-00"));
        save(new Lesson(null, "General - HUD Fault Test",
                "2026/06/26", "Unpublished", "Alice Johnson", "737-8", "00-"));
        save(new Lesson(null, "New Lesson",
                "2026/06/25", "Unpublished", "Bob", "737-8", "-"));
        save(new Lesson(null, "New Lesson",
                "2026/06/25", "Unpublished", "James Lin", "737-8", "-"));
    }

    // ===== CRUD OPERATIONS =====

    /**
     * Get all lessons.
     */
    public List<Lesson> getAllLessons() {
        return new ArrayList<>(lessonStore.values());
    }

    /**
     * Get a single lesson by ID.
     * Returns Optional — forces the caller to handle the "not found" case.
     */
    public Optional<Lesson> getLessonById(Long id) {
        return Optional.ofNullable(lessonStore.get(id));
    }

    /**
     * Create a new lesson.
     * Business logic: set defaults for status and dateModified if not provided.
     */
    public Lesson createLesson(Lesson lesson) {
        // Business rule: new lessons default to "Unpublished" if no status provided
        if (lesson.getStatus() == null || lesson.getStatus().isBlank()) {
            lesson.setStatus("Unpublished");
        }

        // Business rule: set dateModified to today if not provided
        if (lesson.getDateModified() == null || lesson.getDateModified().isBlank()) {
            lesson.setDateModified(java.time.LocalDate.now().toString().replace("-", "/"));
        }

        // Ensure id is null so save() assigns a new one
        lesson.setId(null);

        return save(lesson);
    }

    /**
     * Update an existing lesson.
     * Returns Optional.empty() if the lesson doesn't exist.
     */
    public Optional<Lesson> updateLesson(Long id, Lesson updatedLesson) {
        if (!lessonStore.containsKey(id)) {
            return Optional.empty();
        }

        // Preserve the ID from the path parameter
        updatedLesson.setId(id);

        // Update the dateModified timestamp
        updatedLesson.setDateModified(java.time.LocalDate.now().toString().replace("-", "/"));

        lessonStore.put(id, updatedLesson);
        return Optional.of(updatedLesson);
    }

    /**
     * Delete a lesson by ID.
     * Returns true if deleted, false if not found.
     */
    public boolean deleteLesson(Long id) {
        return lessonStore.remove(id) != null;
    }

    // ===== PRIVATE HELPER =====

    /**
     * Internal save — assigns ID if new, then stores in the HashMap.
     */
    private Lesson save(Lesson lesson) {
        if (lesson.getId() == null) {
            lesson.setId(idCounter.getAndIncrement());
        }
        lessonStore.put(lesson.getId(), lesson);
        return lesson;
    }
}

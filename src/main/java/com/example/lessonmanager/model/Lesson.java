package com.example.lessonmanager.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * ============================================================
 * LESSON MODEL (Data Transfer Object / Domain Object)
 * ============================================================
 *
 * This class represents a single Lesson in our airplane lesson manager.
 * Based on the screenshot, each lesson has:
 * - title          (e.g., "INTRODUCTION: Tutorial - HUD Fault Test")
 * - dateModified   (e.g., "2026/06/29")
 * - status         (e.g., "Unpublished")
 * - author         (e.g., "John Doe")
 * - model          (e.g., "737-8")
 * - ataNumber      (e.g., "00-00")
 *
 * KEY CONCEPTS:
 * - This is a POJO (Plain Old Java Object) - just fields + getters/setters
 * - No database annotations (we're not using JPA yet)
 * - The @Schema annotations are for Swagger documentation only
 * - We also add an 'id' field so we can uniquely identify each lesson
 */
@Schema(description = "Represents an airplane training lesson")
public class Lesson {

    @Schema(description = "Unique identifier for the lesson", example = "1")
    private Long id;

    @Schema(description = "Title of the lesson", example = "INTRODUCTION: Tutorial - HUD Fault Test")
    private String title;

    @Schema(description = "Date the lesson was last modified (YYYY/MM/DD)", example = "2026/06/29")
    private String dateModified;

    @Schema(description = "Publication status of the lesson", example = "Unpublished", allowableValues = {"Unpublished", "Published"})
    private String status;

    @Schema(description = "Author name", example = "John Doe")
    private String author;

    @Schema(description = "Aircraft model", example = "737-8")
    private String model;

    @Schema(description = "ATA chapter number", example = "00-00")
    private String ataNumber;

    // ===== CONSTRUCTORS =====

    /** No-arg constructor (needed by frameworks like Jackson for JSON deserialization) */
    public Lesson() {
    }

    /** All-args constructor for convenience */
    public Lesson(Long id, String title, String dateModified,
                  String status, String author, String model, String ataNumber) {
        this.id = id;
        this.title = title;
        this.dateModified = dateModified;
        this.status = status;
        this.author = author;
        this.model = model;
        this.ataNumber = ataNumber;
    }

    // ===== GETTERS AND SETTERS =====
    // These are required for Jackson (JSON library) to serialize/deserialize objects

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAtaNumber() {
        return ataNumber;
    }

    public void setAtaNumber(String ataNumber) {
        this.ataNumber = ataNumber;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", status='" + status + '\'' +
                ", author='" + author + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}

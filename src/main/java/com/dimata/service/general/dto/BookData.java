package com.dimata.service.general.dto;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@ApplicationScoped
public class BookData {

    private Long id;

    @NotBlank(message = "Book name cannot be empty")
    private String name;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    @NotBlank(message = "Author cannot be empty")
    private String author;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public BookData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

package com.codingtrainers.duocoding.dto.output;


import java.time.LocalDateTime;
import java.util.List;

public class TestDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private List<QuestionDTO> questions;

    public TestDTO(Long id, String name, String description, LocalDateTime createdAt, List<QuestionDTO> questions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.questions = questions;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }
}

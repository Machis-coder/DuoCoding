package com.codingtrainers.duocoding.dto.output;


import java.time.LocalDateTime;
import java.util.List;

public class TestResponseDTO {
    private Long id;
    private String name;
    private String description;
    private Boolean active;
    private Long subjectId;
    private List<QuestionResponseDTO> questions;

    public TestResponseDTO() {}

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public List<QuestionResponseDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionResponseDTO> questions) {
        this.questions = questions;
    }
    public TestResponseDTO(Long id, String name, String description,Boolean active, Long subjectId, List<QuestionResponseDTO> questions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
        this.subjectId = subjectId;
        this.questions = questions;
    }
}

package com.codingtrainers.duocoding.dto.output;

import com.codingtrainers.duocoding.entities.Subject;

public class SubjectResponseDTO {

    private Long id;
    private String name;
    private String description;
    private Boolean active;

    public SubjectResponseDTO() {
    }

    public SubjectResponseDTO(Long id, String name, String description, Boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
    }

    public SubjectResponseDTO(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getName();
        this.description = subject.getDescription();
        this.active = subject.getActive();
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

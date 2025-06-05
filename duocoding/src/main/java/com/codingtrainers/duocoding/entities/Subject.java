package com.codingtrainers.duocoding.entities;

public class Subject {
    Long id;
    String name;
    String description;

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

    public Subject(String name, Long id, String description) {
        this.name = name;
        this.id = id;
        this.description = description;
    }
}

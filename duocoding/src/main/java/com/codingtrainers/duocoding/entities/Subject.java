package com.codingtrainers.duocoding.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;



@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table (name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "name")
    private String name;
    @Column (name = "description")
    private String description;
    @Column (name = "active")
    private Boolean active;

    public Subject() {}

    public Subject(Long id, String name, Boolean active, String description) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
}

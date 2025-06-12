package com.codingtrainers.duocoding.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "test")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public Exercise() {}

    public Exercise(Long id, String name, String description, Subject subject) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.subject = subject;
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    
    public String getSubjectName() {
        return subject != null ? subject.getName() : null;
    }

    public void setSubjectName(String subjectName) {
        if (subject != null) {
        subject.setName(subjectName);
    }
}
}

package com.codingtrainers.duocoding.entities;

import jakarta.persistence.*;

@Entity
public class TestSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private Exercise exercise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public TestSubject() {}

    public TestSubject(Long id, Exercise exercise, Subject subject) {
        this.id = id;
        this.exercise = exercise;
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Exercise getTest() {
        return exercise;
    }

    public void setTest(Exercise exercise) {
        this.exercise = exercise;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

}

package com.codingtrainers.duocoding.entities;

import jakarta.persistence.*;

@Entity
public class TestQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private Exercise exercise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    public TestQuestion() {}

    public TestQuestion(Long id, Exercise exercise, Question question) {
        this.id = id;
        this.exercise = exercise;
        this.question = question;
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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}

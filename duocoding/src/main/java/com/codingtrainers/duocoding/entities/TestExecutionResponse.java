package com.codingtrainers.duocoding.entities;

import jakarta.persistence.*;

@Entity
public class TestExecutionResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_execution_id")
    TestExecution testExecution;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "response_id")
    Response response;

    boolean correct;

    String notes;

    public TestExecutionResponse() {

    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }


    public TestExecution getTestExecution() {
        return testExecution;
    }

    public void setTestExecution(TestExecution testExecution) {
        this.testExecution = testExecution;
    }

    public TestExecutionResponse(Long id, TestExecution testExecution, Question question, Response response, boolean correct, String notes) {
        this.id = id;
        this.testExecution = testExecution;
        this.question = question;
        this.response = response;
        this.correct = correct;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}



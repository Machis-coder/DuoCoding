package com.codingtrainers.duocoding.entities;

import jakarta.persistence.*;

@Entity
@Table (name = "test_execution_response")
public class TestExecutionResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_execution_id")
    private TestExecution testExecution;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @Column (name = "correct")
    private Boolean correct;
    @Column(name = "notes")
    private String notes;

    @Column (name = "active")
    private Boolean active;
    @Column(name = "answer")
    private String answer;

    public TestExecutionResponse() {

    }

    public TestExecutionResponse(Long id, TestExecution testExecution, Question question, Boolean correct, String notes, Boolean active, String answer) {
        this.id = id;
        this.testExecution = testExecution;
        this.question = question;
        this.correct = correct;
        this.notes = notes;
        this.active = active;
        this.answer = answer;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }




    public TestExecution getTestExecution() {
        return testExecution;
    }

    public void setTestExecution(TestExecution testExecution) {
        this.testExecution = testExecution;
    }




}



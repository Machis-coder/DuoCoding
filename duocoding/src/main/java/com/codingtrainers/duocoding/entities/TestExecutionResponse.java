package com.codingtrainers.duocoding.entities;

public class TestExecutionResponse {

    private int id;
    private TestExecution execution;
    private Question question;
    private String answer;
    private boolean correct;
    private String notes;

    public TestExecutionResponse(int id, TestExecution execution, Question question, String answer, boolean correct, String notes) {
        this.id = id;
        this.execution = execution;
        this.question = question;
        this.answer = answer;
        this.correct = correct;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TestExecution getExecution() {
        return execution;
    }

    public void setExecution(TestExecution execution) {
        this.execution = execution;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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

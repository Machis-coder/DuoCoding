package com.codingtrainers.duocoding.entities;

public class Question {
    private int id;
    private QuestionType type;
    private String description;
    private String answer;

    public Question(int id,QuestionType type, String description, String answer) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

package com.codingtrainers.duocoding.entities;

public class Response {

    private int id;
    private String description;
    private int order;
    private TestQuestion question;

    public Response(int id, String description, int order, TestQuestion question) {
        this.id = id;
        this.description = description;
        this.order = order;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public TestQuestion getQuestion() {
        return question;
    }

    public void setQuestion(TestQuestion question) {
        this.question = question;
    }
}

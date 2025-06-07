package com.codingtrainers.duocoding.entities;

public class Response {

    private Long id;
    private String description;
    private int order;
    private Question question;

    public Response(Long id, String description, int order, Question question) {
        this.id = id;
        this.description = description;
        this.order = order;
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Question getQuestion() {
        return question;
    }

}
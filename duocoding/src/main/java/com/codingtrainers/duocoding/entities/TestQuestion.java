package com.codingtrainers.duocoding.entities;

public class TestQuestion {
    private int id;
    private Test test;
    private Question question;

    public TestQuestion(Test test, Question question, int id) {
        this.test = test;
        this.question = question;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}

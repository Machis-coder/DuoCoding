package com.codingtrainers.duocoding.entities;

public class TestQuestionResult {

    private int id;
    private Test test;
    private Question question;
    private User user;
    private String answer;

    public TestQuestionResult(int id, Test test, Question question, User user, String answer) {
        this.id = id;
        this.test = test;
        this.question = question;
        this.user = user;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

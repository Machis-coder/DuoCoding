package com.codingtrainers.duocoding.entities;

public class TestSubject {

    private int id;
    private Test test;
    private Subject subject;

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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public TestSubject(int id, Test test, Subject subject) {
        this.id = id;
        this.test = test;
        this.subject = subject;
    }
}

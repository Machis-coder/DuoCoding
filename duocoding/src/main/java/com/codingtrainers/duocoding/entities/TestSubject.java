package com.codingtrainers.duocoding.entities;

public class TestSubject {

    private Long id;
    private Test test;
    private Subject subject;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public TestSubject(Long id, Test test, Subject subject) {
        this.id = id;
        this.test = test;
        this.subject = subject;
    }
}

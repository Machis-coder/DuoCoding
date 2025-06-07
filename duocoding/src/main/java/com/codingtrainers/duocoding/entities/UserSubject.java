package com.codingtrainers.duocoding.entities;

public class UserSubject {

    private Long id;
    private User user;
    private Subject subject;

    public UserSubject(Long id, User user, Subject subject) {
        this.id = id;
        this.user = user;
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}

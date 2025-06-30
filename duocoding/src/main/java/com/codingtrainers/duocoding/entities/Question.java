package com.codingtrainers.duocoding.entities;


import jakarta.persistence.*;

@Entity
@Table (name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private QuestionType type;
    @Column(name  = "description")
    private String description;
    @Column (name = "answer")
    private String answer;
    @Column (name = "active")
    private Boolean active;

    public Question() {}

    public Question(Long id, QuestionType type, String description, String answer, Boolean active) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.answer = answer;
        this.active = active;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

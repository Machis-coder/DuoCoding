package com.codingtrainers.duocoding.dto.input;

import com.codingtrainers.duocoding.entities.QuestionType;

public class QuestionRequestDTO {
    private QuestionType type;
    private String description;
    private String answer;
    private Boolean active;

    public QuestionRequestDTO() {}

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}


package com.codingtrainers.duocoding.dto.output;

import com.codingtrainers.duocoding.entities.QuestionType;

import java.util.List;

public class QuestionDTO {
    private String answer;
    private QuestionType type;
    private String description;
    private List<ResponseDTO> responses;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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

    public List<ResponseDTO> getResponses() {
        return responses;
    }

    public void setResponses(List<ResponseDTO> responses) {
        this.responses = responses;
    }
}

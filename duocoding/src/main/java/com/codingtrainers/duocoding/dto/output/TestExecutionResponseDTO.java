package com.codingtrainers.duocoding.dto.output;

import com.codingtrainers.duocoding.entities.TestExecutionResponse;

public class TestExecutionResponseDTO {

    private Long id;
    private Long questionId;
    private String answer;
    private Boolean isCorrect;
    private String notes;

    public TestExecutionResponseDTO(TestExecutionResponse response) {
        this.id = response.getId();
        this.questionId = response.getQuestion().getId();
        this.answer = response.getAnswer();
        this.isCorrect = response.getCorrect();
        this.notes = response.getNotes();
    }

    public TestExecutionResponseDTO() {}

    public Long getId() {
        return id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

package com.codingtrainers.duocoding.dto.output;

public class TestExecutionResponseDTO {

    private Long id;
    private Long questionId;
    private String answer;
    private Boolean isCorrect;
    private String notes;

    public TestExecutionResponseDTO(Long id, Long questionId, String answer, Boolean isCorrect, String notes) {
        this.id = id;
        this.questionId = questionId;
        this.answer = answer;
        this.isCorrect = isCorrect;
        this.notes = notes;
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

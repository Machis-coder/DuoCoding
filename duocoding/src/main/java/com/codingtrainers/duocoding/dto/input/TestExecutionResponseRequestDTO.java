package com.codingtrainers.duocoding.dto.input;



public class TestExecutionResponseRequestDTO {

    private Long questionId;
    private String answer;

    public TestExecutionResponseRequestDTO() {
    }

    public TestExecutionResponseRequestDTO(Long questionId, String answer) {
        this.questionId = questionId;
        this.answer = answer;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

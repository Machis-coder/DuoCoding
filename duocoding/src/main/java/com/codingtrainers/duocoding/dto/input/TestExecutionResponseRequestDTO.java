package com.codingtrainers.duocoding.dto.input;

import java.util.List;

public class TestExecutionResponseRequestDTO {

    private Long questionId;
    private String answer;




    public TestExecutionResponseRequestDTO() {
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

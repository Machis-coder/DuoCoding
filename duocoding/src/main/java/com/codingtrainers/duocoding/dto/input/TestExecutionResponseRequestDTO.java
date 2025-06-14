package com.codingtrainers.duocoding.dto.input;

import java.util.List;

public class TestExecutionResponseRequestDTO {

    private Long questionId;
    private Long responseId;
    private String freeResponse;
    private List<Long> selectedResponseIds;

    public List<Long> getSelectedResponseIds() {
        return selectedResponseIds;
    }

    public TestExecutionResponseRequestDTO() {
    }

    public void setSelectedResponseIds(List<Long> selectedResponseIds) {
        this.selectedResponseIds = selectedResponseIds;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getResponseId() {
        return responseId;
    }

    public void setResponseId(Long responseId) {
        this.responseId = responseId;
    }

    public String getFreeResponse() {
        return freeResponse;
    }

    public void setFreeResponse(String freeResponse) {
        this.freeResponse = freeResponse;
    }

}

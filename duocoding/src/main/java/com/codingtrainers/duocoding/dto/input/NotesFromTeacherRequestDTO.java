package com.codingtrainers.duocoding.dto.input;

public class NotesFromTeacherRequestDTO {

    private String testExecutionResponseNotes;
    private String testExecutionNotes;
    private Long testExecutionId;
    private Long testExecutionResponseId;

    public Long getTestExecutionId() {
        return testExecutionId;
    }

    public void setTestExecutionId(Long testExecutionId) {
        this.testExecutionId = testExecutionId;
    }

    public NotesFromTeacherRequestDTO() {
    }

    public String getTestExecutionResponseNotes() {
        return testExecutionResponseNotes;
    }

    public void setTestExecutionResponseNotes(String testExecutionResponseNotes) {
        this.testExecutionResponseNotes = testExecutionResponseNotes;
    }

    public String getTestExecutionNotes() {
        return testExecutionNotes;
    }

    public void setTestExecutionNotes(String testExecutionNotes) {
        this.testExecutionNotes = testExecutionNotes;
    }

    public Long getTestExecutionResponseId() {
        return testExecutionResponseId;
    }

    public void setTestExecutionResponseId(Long testExecutionResponseId) {
        this.testExecutionResponseId = testExecutionResponseId;
    }
}

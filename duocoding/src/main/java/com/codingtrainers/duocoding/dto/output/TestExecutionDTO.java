package com.codingtrainers.duocoding.dto.output;


import java.time.LocalDateTime;

import java.util.Date;
import java.util.List;

public class TestExecutionDTO {

    private Long id;
    private Long testId;
    private Long userId;
    private Date date;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private float result;
    private String notes;
    private List<TestExecutionResponseDTO> executionResponsesList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public float getResult() {
        return result;
    }

    public void setResult(float result) {
        this.result = result;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<TestExecutionResponseDTO> getExecutionResponsesList() {
        return executionResponsesList;
    }

    public void setExecutionResponsesList(List<TestExecutionResponseDTO> executionResponsesList) {
        this.executionResponsesList = executionResponsesList;
    }

    public TestExecutionDTO() {

    }
}


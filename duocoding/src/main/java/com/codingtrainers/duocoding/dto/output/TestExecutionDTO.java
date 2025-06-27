package com.codingtrainers.duocoding.dto.output;


import com.codingtrainers.duocoding.entities.TestExecution;

import java.time.LocalDate;
import java.time.LocalDateTime;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TestExecutionDTO {

    private Long id;
    private Long testId;
    private Long userId;
    private LocalDate date;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private float result;
    private String notes;
    private List<TestExecutionResponseDTO> executionResponsesList;
    private String testName;

    public TestExecutionDTO(TestExecution execution) {
        this.id = execution.getId();
        this.testId = execution.getTest().getId();
        this.userId = execution.getUser().getId();
        this.date = execution.getDate();
        this.startTime = execution.getStartTime();
        this.endTime = execution.getFinishTime();
        this.result = execution.getResult();
        this.notes = execution.getNotes();
        this.testName = execution.getTest().getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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


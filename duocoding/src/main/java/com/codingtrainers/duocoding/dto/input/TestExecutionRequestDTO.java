package com.codingtrainers.duocoding.dto.input;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;

public class TestExecutionRequestDTO {

    private Long testId;
    private Long userId;
    private LocalDate date;
    private LocalDateTime timeStart;
    private LocalDateTime timeFinish;
    private List<TestExecutionResponseRequestDTO> responses;


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

    public LocalDateTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalDateTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalDateTime getTimeFinish() {
        return timeFinish;
    }

    public void setTimeFinish(LocalDateTime timeFinish) {
        this.timeFinish = timeFinish;
    }

    public List<TestExecutionResponseRequestDTO> getResponses() {
        return responses;
    }

    public void setResponses(List<TestExecutionResponseRequestDTO> responses) {
        this.responses = responses;
    }
}
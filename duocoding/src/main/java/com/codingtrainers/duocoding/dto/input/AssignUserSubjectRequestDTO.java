package com.codingtrainers.duocoding.dto.input;

public class AssignUserSubjectRequestDTO {
    private Long userId;
    private Long subjectId;

    public AssignUserSubjectRequestDTO() {
    }

    public AssignUserSubjectRequestDTO(Long userId, Long subjectId) {
        this.userId = userId;
        this.subjectId = subjectId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
}

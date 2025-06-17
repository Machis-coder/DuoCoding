// ExamStructureDTO.java
package com.codingtrainers.duocoding.dtos;

import java.util.List;

public class ExamStructureDTO {
    public Long testId;
    public String testTitle;
    public List<QuestionDTO> questions;

    public static class QuestionDTO {
        public Long questionId;
        public String content;
        public List<ResponseDTO> responses;
    }

    public static class ResponseDTO {
        public Long responseId;
        public String content;
        private boolean correct;

        public boolean isCorrect() {
            return correct;
        }

        public void setCorrect(boolean correct) {
            this.correct = correct;
        }
    }
    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }

}

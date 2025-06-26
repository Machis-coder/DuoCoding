package com.codingtrainers.duocoding.dto.output;

import java.util.List;

public class TestExecutionFullDTO {
    public Long testId;
    public String testTitle;
    public List<QuestionFullDTO> questions;


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

    public List<QuestionFullDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionFullDTO> questions) {
        this.questions = questions;
    }

}

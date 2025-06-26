package com.codingtrainers.duocoding.services;

import com.codingtrainers.duocoding.entities.Question;
import com.codingtrainers.duocoding.entities.Response;
import com.codingtrainers.duocoding.entities.TestQuestion;
import com.codingtrainers.duocoding.repositories.QuestionRepository;
import com.codingtrainers.duocoding.repositories.ResponseRepository;
import com.codingtrainers.duocoding.repositories.TestQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TestQuestionRepository testQuestionRepository;

    @Autowired
    private ResponseRepository responseRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question Not Found"));
    }

    public Question createQuestion(Question question) {
        if (question == null) {
            throw new IllegalArgumentException("Question cannot be null");
        }
        return questionRepository.save(question);
    }

    public Question updateQuestion(Question question) {
        if (question == null) {
            throw new IllegalArgumentException("Question cannot be null");
        }
        return questionRepository.save(question);
    }

    public String deleteQuestionById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (!questionRepository.existsById(id)) {
            throw new RuntimeException("Question not found");
        }
        questionRepository.deleteById(id);
        return "Question removed successfully";
    }

    public List<TestQuestion> findTestQuestionsByTestId(Long testId) {
        return testQuestionRepository.findAllByTestId(testId);
    }

    public List<Question> findAllByIds(List<Long> ids) {
        return questionRepository.findAllByIdIn(ids);
    }

    public List<Response> findAllResponsesByQuestionIds(List<Long> questionIds) {
        return responseRepository.findAllByQuestionIdIn(questionIds);
    }
}

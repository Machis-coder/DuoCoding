package com.codingtrainers.duocoding.services;

import com.codingtrainers.duocoding.entities.Question;
import com.codingtrainers.duocoding.entities.Response;
import com.codingtrainers.duocoding.entities.TestQuestion;
import com.codingtrainers.duocoding.repositories.QuestionRepository;
import com.codingtrainers.duocoding.repositories.ResponseRepository;
import com.codingtrainers.duocoding.repositories.TestQuestionRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TestQuestionRepository testQuestionRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ResponseRepository responseRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAllByActiveTrue();
    }

    public Question getById(Long id) {
        return questionRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
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
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        question.setActive(false);
        questionRepository.save(question);

        return "Question marked as inactive successfully";
    }

    public List<TestQuestion> findTestQuestionsByTestId(Long testId) {

        return testQuestionRepository.findByTestId(testId);
    }

    public List<Question> findAllByIds(List<Long> ids) {

        return questionRepository.findAllActiveByIdIn(ids);
    }

    public List<Response> findAllResponsesByQuestionIds(List<Long> questionIds) {

        return responseRepository.findAllActiveByQuestionIdIn(questionIds);
    }
}

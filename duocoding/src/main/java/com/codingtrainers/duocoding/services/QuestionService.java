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

    public Optional<Question> getQuestionById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser null");
        }

        Optional<Question> question = questionRepository.findByIdAndActiveTrue(id);

        if (question.isEmpty()) {
            throw new EntityNotFoundException("Pregunta activa no encontrada con id: " + id);
        }

        return question;
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
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("activeFilter").setParameter("isActive", true);

        return testQuestionRepository.findAllByTestId(testId);
    }

    public List<Question> findAllByIds(List<Long> ids) {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("activeFilter").setParameter("isActive", true);
        return questionRepository.findAllActiveByIdIn(ids);
    }

    public List<Response> findAllResponsesByQuestionIds(List<Long> questionIds) {
        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("activeFilter").setParameter("isActive", true);
        return responseRepository.findAllActiveByQuestionIdIn(questionIds);
    }
}

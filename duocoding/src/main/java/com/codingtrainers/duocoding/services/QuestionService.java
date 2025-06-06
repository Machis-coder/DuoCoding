package com.codingtrainers.duocoding.services;

import com.codingtrainers.duocoding.entities.Question;
import com.codingtrainers.duocoding.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.getQuestions();
    }
}

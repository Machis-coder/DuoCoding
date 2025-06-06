package com.codingtrainers.duocoding.repositories;

import com.codingtrainers.duocoding.entities.Question;
import com.codingtrainers.duocoding.entities.QuestionType;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionRepository {

private List<Question> questions;
private QuestionType[] types = QuestionType.values();

@PostConstruct
    public void init() {
    questions = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
        Question question = new Question(
                i,
                types[i % types.length],
                "Esta es la pregunta " + i,
                "La respuesta es " + i);
        questions.add(question);
        }
    }

    public List<Question> getQuestions() {
        return questions;
    }
}


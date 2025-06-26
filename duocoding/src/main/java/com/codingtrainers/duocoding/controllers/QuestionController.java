package com.codingtrainers.duocoding.controllers;

import com.codingtrainers.duocoding.entities.Question;
import com.codingtrainers.duocoding.services.QuestionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable("id") Long id) {
        try {
            Optional<Question> optionalQuestion = Optional.ofNullable(questionService.getById(id));
            return optionalQuestion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/")
    public Question createQuestion(@RequestBody Question question) {
        return questionService.createQuestion(question);
    }

    @PutMapping("/")
    public Question updateQuestion(@RequestBody Question question) {
        return questionService.updateQuestion(question);
    }


    @PutMapping("/{id}/delete")
    public ResponseEntity<String> deleteQuestionById(@PathVariable("id") Long id) {
        String message = questionService.deleteQuestionById(id);
        return ResponseEntity.ok(message);
    }
}

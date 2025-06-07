package com.codingtrainers.duocoding.controllers;

import com.codingtrainers.duocoding.entities.Question;
import com.codingtrainers.duocoding.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Question getQuestionById(@PathVariable("id") Long id) {
        return questionService.getQuestionById(id);
    }

    @PostMapping("/")
    public Question createQuestion(@RequestBody Question question) {
        return questionService.createQuestion(question);
    }

    @PutMapping("/")
    public Question updateQuestion(@RequestBody Question question) {
        return questionService.updateQuestion(question);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestionById(@PathVariable("id") Long id) {
        String message = questionService.deleteQuestionById(id);
        return ResponseEntity.ok(message);
    }
}

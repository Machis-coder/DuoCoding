package com.codingtrainers.duocoding.controllers;

import com.codingtrainers.duocoding.entities.TestQuestion;
import com.codingtrainers.duocoding.services.TestQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/testQuestions")
public class TestQuestionController {
    @Autowired
    TestQuestionService testQuestionService;

    @GetMapping
    public List<TestQuestion> getAllTestQuestions(){return testQuestionService.getALlTestQuestions();}

    @GetMapping("/{id}")
    public TestQuestion getTestQuestionById(@PathVariable Long id){return  testQuestionService.getTestQuestionById(id);}

    @PostMapping
    public TestQuestion createTestQuestion(@RequestBody TestQuestion testQuestion){ return  testQuestionService.createTestQuestion(testQuestion);}

    @PutMapping("/{id}")
    public TestQuestion updateTestQuestion(@RequestBody TestQuestion testQuestion){return  testQuestionService.updateTestQuestion(testQuestion);}

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTestQuestion(@PathVariable Long id){
        String message = testQuestionService.deleteTestQuestion(id);
        return ResponseEntity.ok(message);

    }

}

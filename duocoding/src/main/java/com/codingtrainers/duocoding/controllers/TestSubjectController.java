package com.codingtrainers.duocoding.controllers;

import com.codingtrainers.duocoding.entities.TestQuestion;
import com.codingtrainers.duocoding.entities.TestSubject;
import com.codingtrainers.duocoding.services.TestQuestionService;
import com.codingtrainers.duocoding.services.TestSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/testSubjects")
public class TestSubjectController {
    @Autowired
     private TestSubjectService testSubjectService;

    @GetMapping
    public List<TestSubject> getAllTestSubject(){return testSubjectService.getALlTestSubject();}

    @GetMapping("/{id}")
    public TestSubject getTestSubjectById(@PathVariable Long id){return  testSubjectService.getTestSubjectById(id);}

    @PostMapping
    public TestSubject createTestSubject(@RequestBody TestSubject testSubject){ return  testSubjectService.createTestSubject(testSubject);}

    @PutMapping("/{id}")
    public TestSubject updateTestSubject(@RequestBody TestSubject testSubject){return testSubjectService.updateTestSubject(testSubject)  ;}

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTestSubject(@PathVariable Long id){
        String message = testSubjectService.deleteTestSubject(id);
        return ResponseEntity.ok(message);

    }

}

package com.codingtrainers.duocoding.controllers;

import com.codingtrainers.duocoding.entities.Test;
import com.codingtrainers.duocoding.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tests")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping
    public List<Test> getAllTests() {
        return testService.getAllTests();
    }

    @GetMapping("/{id}")
    public Test getTestById(@PathVariable Long id) {
        return testService.getTestById(id);
    }

    @PostMapping
    public Test createTest(@RequestBody Test test) {
        return testService.createTest(test);
    }

    @PutMapping("/{id}")
    public Test updateTest(@PathVariable Long id, @RequestBody Test test) {
        test.setId(id);
        return testService.updateTest(test);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTest(@PathVariable Long id) {
        String message = testService.deleteTestById(id);
        return ResponseEntity.ok(message);
    }

    public TestController(TestService testService) {
        this.testService = testService;
    }


}


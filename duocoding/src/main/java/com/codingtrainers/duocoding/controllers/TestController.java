package com.codingtrainers.duocoding.controllers;

import com.codingtrainers.duocoding.entities.Exercise;
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
    public List<Exercise> getAllTests() {
        return testService.getAllTests();
    }

    @GetMapping("/{id}")
    public Exercise getTestById(@PathVariable Long id) {
        return testService.getTestById(id);
    }

    @PostMapping
    public Exercise createTest(@RequestBody Exercise exercise) {
        return testService.createTest(exercise);
    }

    @PutMapping("/{id}")
    public Exercise updateTest(@PathVariable Long id, @RequestBody Exercise exercise) {
        exercise.setId(id);
        return testService.updateTest(exercise);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTest(@PathVariable Long id) {
        String message = testService.deleteTestById(id);
        return ResponseEntity.ok(message);
    }
}


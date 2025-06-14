package com.codingtrainers.duocoding.controllers;


import com.codingtrainers.duocoding.dto.input.TestExecutionRequestDTO;
import com.codingtrainers.duocoding.entities.Test;
import com.codingtrainers.duocoding.entities.TestExecution;
import com.codingtrainers.duocoding.entities.User;
import com.codingtrainers.duocoding.services.TestExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/testexecution")
public class TestExecutionController {

    @Autowired
    private TestExecutionService testExecutionService;

    @GetMapping("/")
    public List<TestExecution> getTestExecutions() {
        return testExecutionService.getTestExecutions();
    }

    @GetMapping("/user")
    public List<TestExecution> getTestExecutionByUser(@PathVariable Long userId) {
        return testExecutionService.getTestExecutionByUser(userId);
    }

    @GetMapping("/test")
    public List<TestExecution> getTestExecutionByTest(@PathVariable Long testId) {
        return testExecutionService.getTestExecutionByTest(testId);
    }

    @DeleteMapping("/{id}")
    public void deleteTestExecution(@PathVariable Long id) {
        testExecutionService.deleteTestExecution(id);
    }

    @GetMapping("/{id}")
    public Optional<TestExecution> getTestExecutionById(@PathVariable Long id) {
        return testExecutionService.getTestExecutionById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Void> saveTestExecution(@RequestBody TestExecutionRequestDTO testExecutionRequestDTO) {
        testExecutionService.saveTestExecution(testExecutionRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
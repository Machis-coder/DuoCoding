package com.codingtrainers.duocoding.controllers;


import com.codingtrainers.duocoding.dto.input.NotesFromTeacherRequestDTO;
import com.codingtrainers.duocoding.dto.input.TestExecutionRequestDTO;
import com.codingtrainers.duocoding.dto.output.TestExecutionFullDTO;
import com.codingtrainers.duocoding.entities.TestExecution;
import com.codingtrainers.duocoding.services.TestExecutionService;
import jakarta.persistence.EntityNotFoundException;
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


    @GetMapping("/test")
    public List<TestExecution> getTestExecutionByTest(@PathVariable Long testId) {
        return testExecutionService.getTestExecutionByTest(testId);
    }

    @PutMapping("/{id}/delete")
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

    @PostMapping("/notes")
    public ResponseEntity<Void> saveTestExecutionNotes(@RequestBody NotesFromTeacherRequestDTO notesFromTeacherRequestDTO) {
        testExecutionService.saveNotesFromTeacher(notesFromTeacherRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/users/{userId}/executions")
    public ResponseEntity<List<com.codingtrainers.duocoding.dto.output.TestExecutionDTO>> getTestExecutionsByUserId(@PathVariable Long userId) {
        List<com.codingtrainers.duocoding.dto.output.TestExecutionDTO> testDtos = testExecutionService.getTestExecutionsByUserId(userId);
        return ResponseEntity.ok(testDtos);
    }

    @GetMapping("/{executionId}/structure")
    public ResponseEntity<TestExecutionFullDTO> getTextExecution(@PathVariable Long executionId) {
        try {
            TestExecutionFullDTO dto = testExecutionService.getTestExecution(executionId);
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException enfe) {
            return ResponseEntity.notFound().build();
        }
    }
}
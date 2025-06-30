package com.codingtrainers.duocoding.controllers;

import com.codingtrainers.duocoding.dto.input.TestRequestDTO;
import com.codingtrainers.duocoding.dto.output.ExamStructureResponseDTO;
import com.codingtrainers.duocoding.dto.output.TestResponseDTO;
import com.codingtrainers.duocoding.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tests")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping
    public ResponseEntity<List<TestResponseDTO>> getAllTests() {
        List<TestResponseDTO> tests = testService.getAllTestDTOs();
        return ResponseEntity.ok(tests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestResponseDTO> getTestById(@PathVariable Long id) {
        Optional<TestResponseDTO> test = testService.getTestDTOById(id);
        return test.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<TestResponseDTO> createTest(@RequestBody TestRequestDTO testDetails) {
        TestResponseDTO createdTest = testService.createTest(testDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestResponseDTO> updateTest(@PathVariable Long id, @RequestBody TestRequestDTO testDetails) {
        Optional<TestResponseDTO> updatedTest = testService.updateTest(id, testDetails);
        return updatedTest.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/delete")
    public ResponseEntity<String> deleteTest(@PathVariable Long id) {
        try {
            testService.deleteTestById(id);
            return ResponseEntity.ok("Test marked as inactive successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PutMapping("/{id}/activate")
    public ResponseEntity<String> activateTest(@PathVariable Long id) {
        try {
            testService.activateTestById(id);
            return ResponseEntity.ok("Test marked as inactive successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/{testId}/structure")
    public ResponseEntity<ExamStructureResponseDTO> getExamStructure(@PathVariable Long testId) {
        ExamStructureResponseDTO dto = testService.getExamStructure(testId);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }
}


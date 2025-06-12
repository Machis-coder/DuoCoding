package com.codingtrainers.duocoding.services;

import com.codingtrainers.duocoding.entities.Exercise;
import com.codingtrainers.duocoding.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    public List<Exercise> getAllTests() {
        return testRepository.findAll();
    }

    public Exercise getTestById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return testRepository.findById(id).orElseThrow(() -> new RuntimeException("Test not found"));
    }

    public Exercise createTest(Exercise exercise) {
        if (exercise == null) {
            throw new IllegalArgumentException("Test cannot be null");
        }
        return testRepository.save(exercise);
    }

    public Exercise updateTest(Exercise exercise) {
        if (exercise == null) {
            throw new IllegalArgumentException("Test cannot be null");
        }
        return testRepository.save(exercise);
    }

    public String deleteTestById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (!testRepository.existsById(id)) {
            throw new RuntimeException("Test not found");
        }
        testRepository.deleteById(id);
        return "Test eliminado con éxito";
    }
}

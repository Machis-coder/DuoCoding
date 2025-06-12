package com.codingtrainers.duocoding.services;

import com.codingtrainers.duocoding.entities.Test;
import com.codingtrainers.duocoding.entities.TestExecution;
import com.codingtrainers.duocoding.entities.User;
import com.codingtrainers.duocoding.repositories.TestExecutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestExecutionService {

    @Autowired
    private TestExecutionRepository testExecutionRepository;

    public List<TestExecution> getTestExecutions() {
        return testExecutionRepository.findAll();
    }

    public List<TestExecution> getTestExecutionByUser(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        User user = new User();
        user.setId(userId);
        return testExecutionRepository.findByUser(user);
    }

    public List<TestExecution> getTestExecutionByTest(Long testId) {
        if (testId == null) {
            throw new IllegalArgumentException("Test ID cannot be null");
        }
        Test test = new Test();
        test.setId(testId);
        return testExecutionRepository.findByTest(test);
    }

    public void deleteTestExecution(Long testExecutionId) {
        if (testExecutionId == null) {
            throw new IllegalArgumentException("TestExecution ID cannot be null");
        }
        testExecutionRepository.deleteById(testExecutionId);
    }

    public Optional<TestExecution> getTestExecutionById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return testExecutionRepository.findById(id);
    }

    public TestExecution saveTestExecution(Long userId, Long testId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        if (testId == null) {
            throw new IllegalArgumentException("Test ID cannot be null");
        }

        User user = new User();
        user.setId(userId);

        Test test = new Test();
        test.setId(testId);

        TestExecution testExecution = new TestExecution();
        testExecution.setUser(user);
        testExecution.setTest(test);

        return testExecutionRepository.save(testExecution);
    }
}

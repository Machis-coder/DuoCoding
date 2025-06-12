package com.codingtrainers.duocoding.services;
import com.codingtrainers.duocoding.entities.Exercise;
import org.junit.jupiter.api.Test;
import com.codingtrainers.duocoding.entities.TestExecution;
import com.codingtrainers.duocoding.entities.User;
import com.codingtrainers.duocoding.repositories.TestExecutionRepository;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestExecutionServiceTest {

    @Mock
    private TestExecutionRepository testExecutionRepository;

    @InjectMocks
    private TestExecutionService testExecutionService;

    private TestExecution sampleExecution;
    private User sampleUser;
    private Exercise sampleTest;

    @BeforeEach
    void setUp() {
        sampleUser = new User();
        sampleUser.setId(1L);

        sampleTest = new Exercise();
        sampleTest.setId(1L);

        sampleExecution = new TestExecution();
        sampleExecution.setId(1L);
        sampleExecution.setUser(sampleUser);
        sampleExecution.setTest(sampleTest);
    }

    @Test
    void getTestExecutions_returnsList() {
        when(testExecutionRepository.findAll()).thenReturn(List.of(sampleExecution));
        List<TestExecution> result = testExecutionService.getTestExecutions();
        assertEquals(1, result.size());
        verify(testExecutionRepository).findAll();
    }

    @Test
    void getTestExecutions_returnsEmptyList() {
        when(testExecutionRepository.findAll()).thenReturn(Collections.emptyList());
        List<TestExecution> result = testExecutionService.getTestExecutions();
        assertTrue(result.isEmpty());
    }

    @Test
    void getTestExecutionByUser_returnsExecutions() {
        when(testExecutionRepository.findByUser(any(User.class))).thenReturn(List.of(sampleExecution));
        List<TestExecution> result = testExecutionService.getTestExecutionByUser(1L);
        assertEquals(1L, result.get(0).getUser().getId());
    }

    @Test
    void getTestExecutionByUser_throwsExceptionOnNullId() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                testExecutionService.getTestExecutionByUser(null));
        assertEquals("User ID cannot be null", exception.getMessage());
    }

    @Test
    void getTestExecutionByTest_returnsExecutions() {
        when(testExecutionRepository.findByTest(any(Exercise.class))).thenReturn(List.of(sampleExecution));
        List<TestExecution> result = testExecutionService.getTestExecutionByTest(1L);
        assertEquals(1L, result.get(0).getTest().getId());
    }

    @Test
    void getTestExecutionByTest_throwsExceptionOnNullId() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                testExecutionService.getTestExecutionByTest(null));
        assertEquals("Test ID cannot be null", exception.getMessage());
    }

    @Test
    void deleteTestExecution_executesSuccessfully() {
        doNothing().when(testExecutionRepository).deleteById(1L);
        testExecutionService.deleteTestExecution(1L);
        verify(testExecutionRepository).deleteById(1L);
    }

    @Test
    void deleteTestExecution_throwsExceptionOnNullId() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                testExecutionService.deleteTestExecution(null));
        assertEquals("TestExecution ID cannot be null", exception.getMessage());
    }

    @Test
    void deleteTestExecution_bubblesUpRepositoryException() {
        doThrow(new RuntimeException("DB Error")).when(testExecutionRepository).deleteById(1L);
        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                testExecutionService.deleteTestExecution(1L));
        assertEquals("DB Error", ex.getMessage());
    }

    @Test
    void getTestExecutionById_returnsOptional() {
        when(testExecutionRepository.findById(1L)).thenReturn(Optional.of(sampleExecution));
        Optional<TestExecution> result = testExecutionService.getTestExecutionById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void getTestExecutionById_returnsEmptyIfNotFound() {
        when(testExecutionRepository.findById(99L)).thenReturn(Optional.empty());
        Optional<TestExecution> result = testExecutionService.getTestExecutionById(99L);
        assertFalse(result.isPresent());
    }

    @Test
    void getTestExecutionById_throwsExceptionOnNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                testExecutionService.getTestExecutionById(null));
        assertEquals("ID cannot be null", exception.getMessage());
    }

    @Test
    void saveTestExecution_savesSuccessfully() {
        when(testExecutionRepository.save(any(TestExecution.class))).thenReturn(sampleExecution);
        TestExecution result = testExecutionService.saveTestExecution(1L, 1L);
        assertEquals(1L, result.getUser().getId());
        assertEquals(1L, result.getTest().getId());
    }

    @Test
    void saveTestExecution_throwsExceptionIfUserIdNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                testExecutionService.saveTestExecution(null, 1L));
        assertEquals("User ID cannot be null", exception.getMessage());
    }

    @Test
    void saveTestExecution_throwsExceptionIfTestIdNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                testExecutionService.saveTestExecution(1L, null));
        assertEquals("Test ID cannot be null", exception.getMessage());
    }

    @Test
    void saveTestExecution_throwsExceptionIfBothIdsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                testExecutionService.saveTestExecution(null, null));
        assertEquals("User ID cannot be null", exception.getMessage());
    }
}

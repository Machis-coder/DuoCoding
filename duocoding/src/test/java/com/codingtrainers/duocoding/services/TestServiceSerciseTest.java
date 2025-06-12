package com.codingtrainers.duocoding.services;

import com.codingtrainers.duocoding.entities.Exercise;
import com.codingtrainers.duocoding.repositories.TestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestServiceSerciseTest {

    @Mock
    private TestRepository testRepository;

    @InjectMocks
    private TestService testService;

    private Exercise sampleExercise;

    @BeforeEach
    void setUp() {
        sampleExercise = new Exercise();
        sampleExercise.setId(1L);
        sampleExercise.setName("Test de Matemáticas");
        sampleExercise.setDescription("Evaluación de conocimientos básicos");
    }

    @Test
    void getAllTests_returnsList() {
        when(testRepository.findAll()).thenReturn(List.of(sampleExercise));

        List<Exercise> result = testService.getAllTests();

        assertEquals(1, result.size());
        verify(testRepository).findAll();
    }

    @Test
    void getAllTests_returnsEmptyList() {
        when(testRepository.findAll()).thenReturn(Collections.emptyList());

        List<Exercise> result = testService.getAllTests();

        assertTrue(result.isEmpty());
    }

    @Test
    void getTestById_returnsTest() {
        when(testRepository.findById(1L)).thenReturn(Optional.of(sampleExercise));

        Exercise result = testService.getTestById(1L);

        assertEquals("Test de Matemáticas", result.getName());
        verify(testRepository).findById(1L);
    }

    @Test
    void getTestById_throwsExceptionWhenNotFound() {
        when(testRepository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            testService.getTestById(2L);
        });

        assertEquals("Test not found", exception.getMessage());
    }

    @Test
    void createTest_savesAndReturnsTest() {
        when(testRepository.save(sampleExercise)).thenReturn(sampleExercise);

        Exercise result = testService.createTest(sampleExercise);

        assertNotNull(result);
        assertEquals("Test de Matemáticas", result.getName());
        verify(testRepository).save(sampleExercise);
    }

    @Test
    void updateTest_savesAndReturnsTest() {
        sampleExercise.setName("Nuevo nombre");
        sampleExercise.setDescription("Descripción actualizada");

        when(testRepository.save(sampleExercise)).thenReturn(sampleExercise);

        Exercise result = testService.updateTest(sampleExercise);

        assertEquals("Nuevo nombre", result.getName());
        assertEquals("Descripción actualizada", result.getDescription());
        verify(testRepository).save(sampleExercise);
    }

    @Test
    void deleteTestById_deletesSuccessfully() {
        when(testRepository.existsById(1L)).thenReturn(true);
        doNothing().when(testRepository).deleteById(1L);

        String message = testService.deleteTestById(1L);

        assertEquals("Test eliminado con éxito", message);
        verify(testRepository).existsById(1L);
        verify(testRepository).deleteById(1L);
    }

    @Test
    void deleteTestById_throwsExceptionWhenNotFound() {
        when(testRepository.existsById(99L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            testService.deleteTestById(99L);
        });

        assertEquals("Test not found", exception.getMessage());
        verify(testRepository).existsById(99L);
        verify(testRepository, never()).deleteById(anyLong());
    }

    @Test
    void getTestById_throwsExceptionIfIdIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            testService.getTestById(null);
        });
        assertEquals("ID cannot be null", exception.getMessage());
    }

    @Test
    void createTest_throwsExceptionIfTestIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            testService.createTest(null);
        });
        assertEquals("Test cannot be null", exception.getMessage());
    }

    @Test
    void updateTest_throwsExceptionIfTestIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            testService.updateTest(null);
        });
        assertEquals("Test cannot be null", exception.getMessage());
    }

    @Test
    void deleteTestById_throwsExceptionIfIdIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            testService.deleteTestById(null);
        });
        assertEquals("ID cannot be null", exception.getMessage());
    }
}

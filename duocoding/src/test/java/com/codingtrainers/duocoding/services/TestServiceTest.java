package com.codingtrainers.duocoding.services;

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
public class TestServiceTest {

    @Mock
    private TestRepository testRepository;

    @InjectMocks
    private TestService testService;

    private com.codingtrainers.duocoding.entities.Test sampleTest;

    @BeforeEach
    void setUp() {
        sampleTest = new com.codingtrainers.duocoding.entities.Test();
        sampleTest.setId(1L);
        sampleTest.setName("Test de Matemáticas");
        sampleTest.setDescription("Evaluación de conocimientos básicos");
    }

    @Test
    void getAllTests_returnsList() {
        when(testRepository.findAll()).thenReturn(List.of(sampleTest));

        List<com.codingtrainers.duocoding.entities.Test> result = testService.getAllTests();

        assertEquals(1, result.size());
        verify(testRepository).findAll();
    }

    @Test
    void getAllTests_returnsEmptyList() {
        when(testRepository.findAll()).thenReturn(Collections.emptyList());

        List<com.codingtrainers.duocoding.entities.Test> result = testService.getAllTests();

        assertTrue(result.isEmpty());
    }

    @Test
    void getTestById_returnsTest() {
        when(testRepository.findById(1L)).thenReturn(Optional.of(sampleTest));

        Test result = (Test) testService.getTestById(1L);

        assertEquals("Test de Matemáticas", ((com.codingtrainers.duocoding.entities.Test) result).getName());
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
        when(testRepository.save(sampleTest)).thenReturn(sampleTest);

        Test result = (Test) testService.createTest(sampleTest);

        assertNotNull(result);
        assertEquals("Test de Matemáticas", ((com.codingtrainers.duocoding.entities.Test) result).getName());
        verify(testRepository).save(sampleTest);
    }

    @Test
void updateTest_savesAndReturnsTest() {
    sampleTest.setName("Nuevo nombre");
    sampleTest.setDescription("Descripción actualizada");

    when(testRepository.save(sampleTest)).thenReturn(sampleTest);

    Test result = (Test) testService.updateTest(sampleTest);

    assertEquals("Nuevo nombre", ((com.codingtrainers.duocoding.entities.Test) result).getName());
    assertEquals("Descripción actualizada", ((com.codingtrainers.duocoding.entities.Test) result).getDescription());
    verify(testRepository).save(sampleTest);
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

package com.codingtrainers.duocoding.services;

import com.codingtrainers.duocoding.entities.Subject;
import com.codingtrainers.duocoding.repositories.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SubjectServiceTest {

    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private SubjectService subjectService;

    private Subject sampleSubject;

    @BeforeEach
    void setUp() {
        sampleSubject = new Subject();
        sampleSubject.setId(1L);
        sampleSubject.setName("Mathematics");
    }

    @Test
    void getAllSubjects_returnsList() {
        when(subjectRepository.findAll()).thenReturn(Arrays.asList(sampleSubject));

        List<Subject> subjects = subjectService.getAllSubjects();

        assertEquals(1, subjects.size());
        assertEquals("Mathematics", subjects.get(0).getName());
        verify(subjectRepository, times(1)).findAll();
    }

    @Test
    void getAllSubjects_returnsEmptyListWhenNoneExist() {
        when(subjectRepository.findAll()).thenReturn(List.of());

        List<Subject> subjects = subjectService.getAllSubjects();

        assertTrue(subjects.isEmpty());
        verify(subjectRepository, times(1)).findAll();
    }

    @Test
    void getById_returnsSubject() {
        when(subjectRepository.findById(1L)).thenReturn(Optional.of(sampleSubject));

        Subject subject = subjectService.getById(1L);

        assertEquals("Mathematics", subject.getName());
        verify(subjectRepository, times(1)).findById(1L);
    }

    @Test
    void getById_throwsExceptionWhenNotFound() {
        when(subjectRepository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> subjectService.getById(2L));

        assertEquals("Subject Not Found", ex.getMessage());
        verify(subjectRepository, times(1)).findById(2L);
    }

    @Test
    void createSubject_savesAndReturnsSubject() {
        when(subjectRepository.save(sampleSubject)).thenReturn(sampleSubject);

        Subject created = subjectService.createSubject(sampleSubject);

        assertEquals("Mathematics", created.getName());
        verify(subjectRepository, times(1)).save(sampleSubject);
    }

    @Test
    void updateSubject_savesAndReturnsUpdatedSubject() {
        sampleSubject.setName("Updated Subject");
        when(subjectRepository.save(sampleSubject)).thenReturn(sampleSubject);

        Subject updated = subjectService.updateSubject(sampleSubject);

        assertEquals("Updated Subject", updated.getName());
        verify(subjectRepository, times(1)).save(sampleSubject);
    }

    @Test
    void deleteTestQuestion_deletesIfExists() {
        when(subjectRepository.existsById(1L)).thenReturn(true);
        doNothing().when(subjectRepository).deleteById(1L);

        String result = subjectService.deleteTestQuestion(1L);

        assertEquals("Subject removed successfully", result);
        verify(subjectRepository, times(1)).existsById(1L);
        verify(subjectRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteTestQuestion_throwsExceptionIfNotFound() {
        when(subjectRepository.existsById(2L)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> subjectService.deleteTestQuestion(2L));

        assertEquals("Subject not found", ex.getMessage());
        verify(subjectRepository, times(1)).existsById(2L);
    }

    @Test
    void deleteTestQuestion_repositoryThrowsException_bubblesUp() {
        when(subjectRepository.existsById(1L)).thenThrow(new RuntimeException("DB error"));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> subjectService.deleteTestQuestion(1L));

        assertEquals("DB error", ex.getMessage());
        verify(subjectRepository, times(1)).existsById(1L);
    }

    @Test
    void fullFlow_createFetchAndDeleteSubject() {
        when(subjectRepository.save(sampleSubject)).thenReturn(sampleSubject);
        Subject created = subjectService.createSubject(sampleSubject);
        assertEquals("Mathematics", created.getName());

        when(subjectRepository.findById(1L)).thenReturn(Optional.of(sampleSubject));
        Subject fetched = subjectService.getById(1L);
        assertEquals("Mathematics", fetched.getName());

        when(subjectRepository.existsById(1L)).thenReturn(true);
        String result = subjectService.deleteTestQuestion(1L);
        assertEquals("Subject removed successfully", result);
    }
}

package com.codingtrainers.duocoding.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import com.codingtrainers.duocoding.entities.Question;
import com.codingtrainers.duocoding.entities.QuestionType;
import com.codingtrainers.duocoding.repositories.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuestionService questionService;

    private Question sampleQuestion;

    @BeforeEach
    void setUp() {
        sampleQuestion = new Question();
        sampleQuestion.setId(1L);
        sampleQuestion.setType(QuestionType.FREETEXT);
    }

    @Test
    void getAllQuestions_returnsList() {
        when(questionRepository.findAll()).thenReturn(Arrays.asList(sampleQuestion));

        List<Question> questions = questionService.getAllQuestions();

        assertEquals(1, questions.size());
        assertEquals(QuestionType.FREETEXT, questions.get(0).getType());
        verify(questionRepository, times(1)).findAll();
    }

    @Test
    void getAllQuestions_returnsEmptyListWhenNoQuestionsExist() {
        when(questionRepository.findAll()).thenReturn(List.of());

        List<Question> questions = questionService.getAllQuestions();

        assertEquals(0, questions.size());
        verify(questionRepository, times(1)).findAll();
    }

    @Test
    void getById_returnsQuestion() {
        when(questionRepository.findById(1L)).thenReturn(java.util.Optional.of(sampleQuestion));

        Question question = questionService.getById(1L);

        assertEquals(QuestionType.FREETEXT, question.getType());
        verify(questionRepository, times(1)).findById(1L);
    }

    @Test
    void getById_throwsExceptionWhenNotFound() {
        when(questionRepository.findById(2L)).thenReturn(java.util.Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            questionService.getById(2L);
        });

        assertEquals("Question Not Found", exception.getMessage());
        verify(questionRepository, times(1)).findById(2L);
    }

    @Test
    void getById_withNullId_throwsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            questionService.getById(null);
        });

        assertEquals("ID cannot be null", exception.getMessage());
    }

    @Test
    void createQuestion_savesAndReturnsQuestion() {
        when(questionRepository.save(sampleQuestion)).thenReturn(sampleQuestion);

        Question created = questionService.createQuestion(sampleQuestion);

        assertEquals(QuestionType.FREETEXT, created.getType());
        verify(questionRepository, times(1)).save(sampleQuestion);
    }

    @Test
    void createQuestion_withNull_throwsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            questionService.createQuestion(null);
        });

        assertEquals("Question cannot be null", exception.getMessage());
    }

    @Test
    void updateQuestion_savesAndReturnsQuestion() {
        when(questionRepository.save(sampleQuestion)).thenReturn(sampleQuestion);

        Question updated = questionService.updateQuestion(sampleQuestion);

        assertEquals(QuestionType.FREETEXT, updated.getType());
        verify(questionRepository, times(1)).save(sampleQuestion);
    }

    @Test
    void updateQuestion_withNull_throwsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            questionService.updateQuestion(null);
        });

        assertEquals("Question cannot be null", exception.getMessage());
    }

    @Test
    void updateQuestion_whenQuestionDoesNotExist_stillSaves() {
        sampleQuestion.setId(999L);
        when(questionRepository.save(sampleQuestion)).thenReturn(sampleQuestion);

        Question result = questionService.updateQuestion(sampleQuestion);

        assertEquals(QuestionType.FREETEXT, result.getType());
        verify(questionRepository, times(1)).save(sampleQuestion);
    }

    @Test
    void deleteQuestionById_deletesIfExists() {
        when(questionRepository.existsById(1L)).thenReturn(true);

        String message = questionService.deleteQuestionById(1L);

        assertEquals("Question removed successfully", message);
        verify(questionRepository, times(1)).existsById(1L);
        verify(questionRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteQuestionById_throwsExceptionIfNotFound() {
        when(questionRepository.existsById(2L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            questionService.deleteQuestionById(2L);
        });

        assertEquals("Question not found", exception.getMessage());
        verify(questionRepository, times(1)).existsById(2L);
    }

    @Test
    void deleteQuestionById_withNullId_throwsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            questionService.deleteQuestionById(null);
        });

        assertEquals("ID cannot be null", exception.getMessage());
    }

    @Test
    void deleteQuestionById_repositoryThrowsException_bubblesUp() {
        when(questionRepository.existsById(1L)).thenThrow(new RuntimeException("Database error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            questionService.deleteQuestionById(1L);
        });

        assertEquals("Database error", exception.getMessage());
        verify(questionRepository, times(1)).existsById(1L);
    }

    @Test
    void fullFlow_createAndFetchAndDeleteQuestion() {

        when(questionRepository.save(sampleQuestion)).thenReturn(sampleQuestion);
        Question created = questionService.createQuestion(sampleQuestion);
        assertEquals(QuestionType.FREETEXT, created.getType());

        when(questionRepository.findById(1L)).thenReturn(java.util.Optional.of(sampleQuestion));
        Question fetched = questionService.getById(1L);
        assertEquals(QuestionType.FREETEXT, fetched.getType());

        when(questionRepository.existsById(1L)).thenReturn(true);
        String message = questionService.deleteQuestionById(1L);
        assertEquals("Question removed successfully", message);
    }
}

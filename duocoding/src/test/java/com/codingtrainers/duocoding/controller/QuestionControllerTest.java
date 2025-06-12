package com.codingtrainers.duocoding.controller;

import com.codingtrainers.duocoding.controllers.QuestionController;
import com.codingtrainers.duocoding.entities.Question;
import com.codingtrainers.duocoding.services.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuestionControllerTest {

    @InjectMocks
    private QuestionController questionController;

    @Mock
    private QuestionService questionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllQuestions_ShouldReturnListOfQuestions() {
        List<Question> mockQuestions = Arrays.asList(
                new Question(1L, "Question 1"),
                new Question(2L, "Question 2")
        );
        when(questionService.getAllQuestions()).thenReturn(mockQuestions);

        List<Question> questions = questionController.getAllQuestions();

        assertNotNull(questions);
        assertEquals(2, questions.size());
        verify(questionService, times(1)).getAllQuestions();
    }


    @Test
    void deleteQuestionById_ShouldReturnMessage() {
        String expectedMessage = "Question deleted successfully";
        when(questionService.deleteQuestionById(1L)).thenReturn(expectedMessage);

        ResponseEntity<String> response = questionController.deleteQuestionById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedMessage, response.getBody());
        verify(questionService, times(1)).deleteQuestionById(1L);
    }
}

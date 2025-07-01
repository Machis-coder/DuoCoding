package com.codingtrainers.duocoding.services;

import com.codingtrainers.duocoding.dto.input.NotesFromTeacherRequestDTO;
import com.codingtrainers.duocoding.dto.input.TestExecutionRequestDTO;
import com.codingtrainers.duocoding.dto.input.TestExecutionResponseRequestDTO;

import com.codingtrainers.duocoding.dto.output.TestExecutionDTO;
import com.codingtrainers.duocoding.dto.output.TestExecutionResponseDTO;
import com.codingtrainers.duocoding.entities.Question;
import com.codingtrainers.duocoding.entities.TestExecution;
import com.codingtrainers.duocoding.entities.TestExecutionResponse;
import com.codingtrainers.duocoding.entities.User;
import com.codingtrainers.duocoding.repositories.QuestionRepository;
import com.codingtrainers.duocoding.repositories.TestExecutionRepository;
import com.codingtrainers.duocoding.repositories.TestExecutionResponseRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestExecutionServiceTest {

    @Mock
    private TestExecutionRepository testExecutionRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private TestExecutionResponseRepository testExecutionResponseRepository;

    @InjectMocks
    private TestExecutionService testExecutionService;

    @Test
    void saveTestExecutionTest() {
        TestExecutionResponseRequestDTO responseDTO1 = new TestExecutionResponseRequestDTO();
        responseDTO1.setQuestionId(1L);
        responseDTO1.setAnswer("3");

        TestExecutionResponseRequestDTO responseDTO2 = new TestExecutionResponseRequestDTO();
        responseDTO2.setQuestionId(2L);
        responseDTO2.setAnswer("3");

        TestExecutionRequestDTO requestDTO = new TestExecutionRequestDTO();
        requestDTO.setDate(null);
        requestDTO.setTestId(1L);
        requestDTO.setUserId(1L);
        requestDTO.setResponses(List.of(responseDTO1, responseDTO2));

        Question question1 = new Question();
        question1.setId(1L);
        question1.setAnswer("3");

        Question question2 = new Question();
        question2.setId(2L);
        question2.setAnswer("4");

        when(testExecutionRepository.save(Mockito.any(TestExecution.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        when(questionRepository.findAllActiveByIdIn(List.of(1L, 2L)))
                .thenReturn(List.of(question1, question2));

        when(testExecutionResponseRepository.save(Mockito.any(TestExecutionResponse.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        testExecutionService.saveTestExecution(requestDTO);

        ArgumentCaptor<TestExecution> testExecutionCaptor = ArgumentCaptor.forClass(TestExecution.class);
        verify(testExecutionRepository, atLeastOnce()).save(testExecutionCaptor.capture());
        TestExecution savedExecution = testExecutionCaptor.getValue();

        ArgumentCaptor<TestExecutionResponse> responseCaptor = ArgumentCaptor.forClass(TestExecutionResponse.class);
        verify(testExecutionResponseRepository, times(2)).save(responseCaptor.capture());

        List<TestExecutionResponse> savedResponses = responseCaptor.getAllValues();
        for (TestExecutionResponse response : savedResponses) {
            assertEquals(savedExecution, response.getTestExecution());
        }

        verify(questionRepository, times(1)).findAllActiveByIdIn(List.of(1L, 2L));
    }

    @Test
    void notesFromTeacherTest() {
        NotesFromTeacherRequestDTO notes = new NotesFromTeacherRequestDTO();
        notes.setTestExecutionId(1L);
        notes.setTestExecutionNotes("test note");
        notes.setTestExecutionResponseId(1L);
        notes.setTestExecutionResponseNotes("response note");

        TestExecution testExecution = new TestExecution();
        TestExecutionResponse testExecutionResponse = new TestExecutionResponse();

        when(testExecutionRepository.findActiveById(1L)).thenReturn(Optional.of(testExecution));
        when(testExecutionResponseRepository.findActiveById(1L)).thenReturn(Optional.of(testExecutionResponse));

        testExecutionService.saveNotesFromTeacher(notes);

        assertEquals("test note", testExecution.getNotes());
        assertEquals("response note", testExecutionResponse.getNotes());

        verify(testExecutionRepository, atLeastOnce()).findActiveById(1L);
        verify(testExecutionResponseRepository, atLeastOnce()).findActiveById(1L);
    }

    @Test
    public void getTestExecutionByUserIdTest() {
        Long userId = 1L;

        User user = new User();
        user.setId(userId);

        com.codingtrainers.duocoding.entities.Test test = new com.codingtrainers.duocoding.entities.Test();
        test.setId(10L);
        test.setName("test name");

        TestExecution testExecution = new TestExecution();
        testExecution.setUser(user);
        testExecution.setTest(test);
        testExecution.setId(10L);
        testExecution.setNotes("Some notes");
        testExecution.setResult(10F);
        testExecution.setStartTime(LocalDateTime.now().minusHours(1));
        testExecution.setFinishTime(LocalDateTime.now());
        testExecution.setDate(LocalDate.now());

        List<TestExecution> executions = new ArrayList<>();
        executions.add(testExecution);

        when(testExecutionRepository.findActiveByUserId(userId)).thenReturn(executions);

        List<TestExecutionDTO> dtos = testExecutionService.getTestExecutionsByUserId(userId);

        assertNotNull(dtos);
        assertEquals(1, dtos.size());

        TestExecutionDTO dto = dtos.get(0);
//        assertEquals(10L, dto.getId());
        assertEquals("Some notes", dto.getNotes());
        assertEquals(userId, dto.getUserId());
        assertEquals(10L, dto.getTestId());
        assertEquals(10F, dto.getResult());
        assertEquals(LocalDate.now(), dto.getDate());
        assertEquals("test name", dto.getTestName());
        assertNotNull(dto.getStartTime());
        assertNotNull(dto.getEndTime());
        assertNotNull(dto.getDate());
    }

    @Test
    public void testGetTestExecutionsByUserId_NullUserId_ThrowsException() {
        assertThrows(NullPointerException.class, () -> {
            testExecutionService.getTestExecutionsByUserId(null);
        });
    }

    @Test
    void getTestExecutionById_Success() {
        Long testExecutionId = 1L;

        TestExecution testExecution = new TestExecution();
        testExecution.setId(testExecutionId);

        com.codingtrainers.duocoding.entities.Test test = new com.codingtrainers.duocoding.entities.Test();
        test.setId(99L);
        testExecution.setTest(test);

        testExecution.setResult(95.5f);

        User user = new User();
        user.setId(42L);
        testExecution.setUser(user);

        when(testExecutionRepository.findById(testExecutionId))
                .thenReturn(Optional.of(testExecution));

        when(testExecutionResponseRepository.findActiveByTestExecutionId(testExecutionId))
                .thenReturn(Collections.emptyList());

        Optional<TestExecutionDTO> result = testExecutionService.getTestExecutionDTOById(testExecutionId);

        assertTrue(result.isPresent());
        assertEquals(testExecutionId, result.get().getId());
        assertEquals(99L, result.get().getTestId());
        assertEquals(42L, result.get().getUserId());
    }


    @Test
    void getTestExecutionById_NotFound() {
        Long testExecutionId = 1L;

        lenient().when(testExecutionRepository.findById(testExecutionId))
                .thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            testExecutionService.gesTestExecutionById(testExecutionId);
        });

        assertEquals("TestExecution not found", thrown.getMessage());
    }


    @Test
    void getTestExecutionsByUserId_EmptyList() {
        Long userId = 42L;

        when(testExecutionRepository.findActiveByUserId(userId)).thenReturn(Collections.emptyList());

        List<TestExecutionDTO> results = testExecutionService.getTestExecutionsByUserId(userId);

        assertNotNull(results);
        assertTrue(results.isEmpty());

        verify(testExecutionRepository).findActiveByUserId(userId);
    }

    @Test
    void saveTestExecution_WithEmptyResponses() {
        TestExecutionRequestDTO requestDTO = new TestExecutionRequestDTO();
        requestDTO.setTestId(1L);
        requestDTO.setUserId(1L);
        requestDTO.setResponses(Collections.emptyList());

        when(testExecutionRepository.save(Mockito.any(TestExecution.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        when(questionRepository.findAllActiveByIdIn(Collections.emptyList()))
                .thenReturn(Collections.emptyList());

        testExecutionService.saveTestExecution(requestDTO);

        verify(testExecutionRepository).save(Mockito.any(TestExecution.class));
        verify(questionRepository).findAllActiveByIdIn(Collections.emptyList());
        verify(testExecutionResponseRepository, never()).save(Mockito.any(TestExecutionResponse.class));
    }

}


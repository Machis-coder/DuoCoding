package com.codingtrainers.duocoding.services;

import com.codingtrainers.duocoding.dto.input.NotesFromTeacherRequestDTO;
import com.codingtrainers.duocoding.dto.input.TestExecutionRequestDTO;
import com.codingtrainers.duocoding.dto.input.TestExecutionResponseRequestDTO;

import com.codingtrainers.duocoding.dto.output.TestExecutionDTO;
import com.codingtrainers.duocoding.entities.Question;
import com.codingtrainers.duocoding.entities.TestExecution;
import com.codingtrainers.duocoding.entities.TestExecutionResponse;
import com.codingtrainers.duocoding.entities.User;
import com.codingtrainers.duocoding.repositories.QuestionRepository;
import com.codingtrainers.duocoding.repositories.TestExecutionRepository;
import com.codingtrainers.duocoding.repositories.TestExecutionResponseRepository;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

        when(questionRepository.findAllById(List.of(1L, 2L)))
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
        verify(questionRepository, times(1)).findAllById(List.of(1L, 2L));
    }

    @Test
    void notesFromTeacherTest () {
        NotesFromTeacherRequestDTO notes = new NotesFromTeacherRequestDTO();
        notes.setTestExecutionId(1L);
        notes.setTestExecutionNotes("test note");
        notes.setTestExecutionResponseId(1L);
        notes.setTestExecutionResponseNotes("response note");

        TestExecution  testExecution = new TestExecution();
        TestExecutionResponse testExecutionResponse = new TestExecutionResponse();

        when(testExecutionRepository.findById(1L)).thenReturn(Optional.of(testExecution));
        when(testExecutionResponseRepository.findById(1L)).thenReturn(Optional.of(testExecutionResponse));

        testExecutionService.saveNotesFromTeacher(notes);

        assertEquals("test note", notes.getTestExecutionNotes());
        assertEquals("response note", notes.getTestExecutionResponseNotes());
        verify(testExecutionRepository, atLeastOnce()).findById(1L);
        verify(testExecutionResponseRepository, atLeastOnce()).findById(1L);
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
        assertEquals(10L, dto.getResult());
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
}


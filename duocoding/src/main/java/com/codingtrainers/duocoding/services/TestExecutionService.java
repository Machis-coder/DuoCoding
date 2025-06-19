package com.codingtrainers.duocoding.services;

import com.codingtrainers.duocoding.dto.input.NotesFromTeacherRequestDTO;
import com.codingtrainers.duocoding.dto.input.TestExecutionRequestDTO;
import com.codingtrainers.duocoding.dto.input.TestExecutionResponseRequestDTO;
import com.codingtrainers.duocoding.dto.output.TestExecutionDTO;
import com.codingtrainers.duocoding.entities.*;
import com.codingtrainers.duocoding.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TestExecutionService {

    @Autowired
    private TestExecutionRepository testExecutionRepository;

    @Autowired
    private TestExecutionResponseRepository testExecutionResponseRepository;

    @Autowired
    private QuestionRepository questionRepository;


    public List<TestExecution> getTestExecutions() {
        return testExecutionRepository.findAll();
    }


    public List<TestExecution> getTestExecutionByUser(Long userId) {
        User user = new User();
        user.setId(userId);
        return testExecutionRepository.findByUser(user);
    }

    public List<TestExecutionDTO> getTestExecutionsByUserId(Long userId) {
        if (userId == null) {
            throw new NullPointerException("userId is null");
        }
        List<TestExecution> testExecutions = testExecutionRepository.findByUserId(userId);
        return testExecutions.stream().map(testExecution -> {
            TestExecutionDTO testExecutionDTO = new TestExecutionDTO();
            testExecutionDTO.setId(testExecution.getId());
            testExecutionDTO.setNotes(testExecution.getNotes());
            testExecutionDTO.setUserId(testExecution.getUser().getId());
            testExecutionDTO.setTestId(testExecution.getTest().getId());
            testExecutionDTO.setResult(testExecution.getResult());
            testExecutionDTO.setStartTime(testExecution.getStartTime());
            testExecutionDTO.setEndTime(testExecution.getFinishTime());
            testExecutionDTO.setDate(testExecution.getDate());
            testExecutionDTO.setNotes(testExecution.getNotes());
            testExecutionDTO.setTestName(testExecution.getTest().getName());
            return testExecutionDTO;
        }).toList();
    }


    public List<TestExecution> getTestExecutionByTest(Long  testId) {
        Test test = new Test();
        test.setId(testId);
        return testExecutionRepository.findByTest(test);
    }
    public void deleteTestExecution(Long testExecutionId) {
        testExecutionRepository.deleteById(testExecutionId);
    }

    public Optional<TestExecution> getTestExecutionById(Long id) {
        return testExecutionRepository.findById(id);
    }

    public void saveTestExecution(TestExecutionRequestDTO dto) {
        User user = new User();
        user.setId(dto.getUserId());
        Test test = new Test();
        test.setId(dto.getTestId());
        TestExecution testExecution = new TestExecution();
        testExecution.setUser(user);
        testExecution.setTest(test);
        testExecution.setDate(dto.getDate());
        testExecution.setNotes(null);
        testExecution.setFinishTime(dto.getTimeFinish());
        testExecution.setStartTime(dto.getTimeStart());
        testExecution.setResult(0);
        testExecution.setActive(true);

        testExecutionRepository.save(testExecution);
        List<Long> questionIds = dto.getResponses()
                .stream()
                .map(TestExecutionResponseRequestDTO::getQuestionId)
                .collect(Collectors.toList());
        List<Question> questions = questionRepository.findAllById(questionIds);
        Map<Long, Question> questionMap = questions.stream()
                .collect(Collectors.toMap(Question::getId, Function.identity()));
        for (TestExecutionResponseRequestDTO responseDTO : dto.getResponses()) {
            Question question = questionMap.get(responseDTO.getQuestionId());

            if (question == null) {
                throw new RuntimeException("Question not found: " + responseDTO.getQuestionId());
            }
            TestExecutionResponse testExecutionResponse = new TestExecutionResponse();
            testExecutionResponse.setQuestion(question);
            testExecutionResponse.setAnswer(responseDTO.getAnswer());
            testExecutionResponse.setTestExecution(testExecution);
            testExecutionResponse.setActive(true);

            if (testExecutionResponse.getAnswer().equals(question.getAnswer())) {
                testExecutionResponse.setCorrect(true);
                testExecution.setResult(testExecution.getResult() + 1);
            } else {
                testExecutionResponse.setCorrect(false);
            }

            testExecutionResponseRepository.save(testExecutionResponse);
        }
        testExecutionRepository.save(testExecution);
    }

    public void saveNotesFromTeacher(NotesFromTeacherRequestDTO notes) {
        TestExecution testExecution = testExecutionRepository.findById(notes.getTestExecutionId())
                .orElseThrow(() -> new EntityNotFoundException("TestExecution not found with id: " + notes.getTestExecutionId()));

        TestExecutionResponse testExecutionResponse = testExecutionResponseRepository.findById(notes.getTestExecutionResponseId())
                .orElseThrow(() -> new EntityNotFoundException("TestExecutionResponse not found with id: " + notes.getTestExecutionResponseId()));

        testExecutionResponse.setNotes(notes.getTestExecutionResponseNotes());
        testExecution.setNotes(notes.getTestExecutionNotes());

        testExecutionResponseRepository.save(testExecutionResponse);
        testExecutionRepository.save(testExecution);
    }


}

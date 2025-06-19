package com.codingtrainers.duocoding.services;

import com.codingtrainers.duocoding.dto.input.NotesFromTeacherRequestDTO;
import com.codingtrainers.duocoding.dto.input.TestExecutionRequestDTO;
import com.codingtrainers.duocoding.dto.input.TestExecutionResponseRequestDTO;
import com.codingtrainers.duocoding.entities.*;
import com.codingtrainers.duocoding.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
                .orElseThrow(() -> new RuntimeException("TestExecution not found"));
        TestExecutionResponse testExecutionResponse = testExecutionResponseRepository.findById(notes.getTestExecutionResponseId())
                .orElseThrow(() -> new RuntimeException("TestExecutionResponse not found"));
        testExecutionResponse.setNotes(notes.getTestExecutionResponseNotes());
        testExecution.setNotes(notes.getTestExecutionNotes());
        testExecutionRepository.save(testExecution);
    }

}

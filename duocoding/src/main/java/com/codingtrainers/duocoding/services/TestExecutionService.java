package com.codingtrainers.duocoding.services;

import com.codingtrainers.duocoding.dto.input.TestExecutionRequestDTO;
import com.codingtrainers.duocoding.dto.input.TestExecutionResponseRequestDTO;
import com.codingtrainers.duocoding.entities.*;
import com.codingtrainers.duocoding.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TestExecutionService {

    @Autowired
    private TestExecutionRepository testExecutionRepository;

    @Autowired
    private TestExecutionResponseRepository testExecutionResponseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ResponseRepository responseRepository;

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

    public TestExecution saveTestExecution(TestExecutionRequestDTO dto)
    {
        User user = new User();
        user.setId(dto.getUserId());
        Test test = new Test();
        test.setId(dto.getTestId());
        TestExecution testExecution = new TestExecution();
        testExecution.setUser(user);
        testExecution.setTest(test);
        testExecution.setDate(dto.getDate());
        testExecution.setNotes(null);
        testExecution.setTimeFinish(dto.getTimeFinish());
        testExecution.setTimeStart(dto.getTimeStart());
        testExecution.setResult(0);
        List<TestExecutionResponse> responses = new ArrayList<>();
        for(int i=0; i< dto.getResponses().size();i++){
            TestExecutionResponseRequestDTO responseDTO = dto.getResponses().get(i);
            TestExecutionResponse testExecutionResponse = new TestExecutionResponse();

            Optional<Question> questionOpt = questionRepository.findById(responseDTO.getQuestionId());

            if (questionOpt.isPresent()) {
                Question question = questionOpt.get();
                String type = String.valueOf(question.getType());
                testExecutionResponse.setQuestion(question);
                Optional<Response> responseOpt = responseRepository.findById(responseDTO.getResponseId());
                if (responseOpt.isPresent()) {
                    testExecutionResponse.setResponse(responseOpt.get());

                    if(testExecutionResponse.getResponse().getOrder().toString().equalsIgnoreCase(question.getAnswer())) {
                        testExecutionResponse.setCorrect(true);
                        testExecution.setResult(testExecution.getResult() + 1);

                    }else{
                        testExecutionResponse.setCorrect(false);
                    }
                        testExecutionResponse.setResponse(responseOpt.get());
                }  else {
                    throw new RuntimeException("Response not found with ID: " + responseDTO.getResponseId());
                }
            } else {
                throw new RuntimeException("Question not found with ID: " + responseDTO.getQuestionId());
            }
            testExecutionResponseRepository.save(testExecutionResponse);
    }
        return testExecutionRepository.save(testExecution);
    }

}

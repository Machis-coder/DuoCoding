package com.codingtrainers.duocoding.services;

import com.codingtrainers.duocoding.dtos.TestExecutionFullDTO;
import com.codingtrainers.duocoding.entities.Response;
import com.codingtrainers.duocoding.entities.Test;
import com.codingtrainers.duocoding.entities.TestQuestion;
import com.codingtrainers.duocoding.repositories.ResponseRepository;
import com.codingtrainers.duocoding.repositories.TestQuestionRepository;
import com.codingtrainers.duocoding.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    private final TestQuestionRepository testQuestionRepository;
    private final ResponseRepository responseRepository;

    public TestService(TestRepository testRepository,
                       TestQuestionRepository testQuestionRepository,
                       ResponseRepository responseRepository) {
        this.testRepository = testRepository;
        this.testQuestionRepository = testQuestionRepository;
        this.responseRepository = responseRepository;
    }

    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    public Test getTestById(Long id) {
        return testRepository.findById(id).orElseThrow(() -> new RuntimeException("Test not found"));
    }

    public Test createTest(Test test) {
        return testRepository.save(test);
    }

    public Test updateTest(Test test) {
        return testRepository.save(test);
    }

    public String deleteTestById(Long id) {
        if (!testRepository.existsById(id)) {
            throw new RuntimeException("Test not found");
        }
        testRepository.deleteById(id);
        return "Test eliminado con éxito";
    }

    public TestExecutionFullDTO getExamStructure(Long testId) {
        Optional<Test> optionalTest = testRepository.findById(testId);
        if (optionalTest.isEmpty()) {
            return null;
        }

        Test test = optionalTest.get();
        TestExecutionFullDTO dto = new TestExecutionFullDTO();
        dto.setTestId(test.getId());
        dto.setTestTitle(test.getName());

        List<TestQuestion> testQuestions = testQuestionRepository.findByTestId(testId);

        dto.setQuestions(testQuestions.stream().map(tq -> {
            com.codingtrainers.duocoding.entities.Question question = tq.getQuestion();
            TestExecutionFullDTO.QuestionDTO questionDTO = new TestExecutionFullDTO.QuestionDTO();
            questionDTO.questionId = question.getId();
            questionDTO.content = question.getDescription();

            Set<Long> correctAnswerIds = new HashSet<>();
            try {
                if (question.getAnswer() != null && !question.getAnswer().isEmpty()) {
                    String[] parts = question.getAnswer().split(",");
                    for (String part : parts) {
                        correctAnswerIds.add(Long.parseLong(part.trim()));
                    }
                }
            } catch (NumberFormatException e) {

            }

            List<Response> responses = responseRepository.findByQuestionId(question.getId());
            questionDTO.responses = responses.stream().map(r -> {
                TestExecutionFullDTO.ResponseDTO responseDTO = new TestExecutionFullDTO.ResponseDTO();
                responseDTO.responseId = r.getId();
                responseDTO.content = r.getDescription();
                responseDTO.setCorrect(correctAnswerIds.contains(r.getId()));
                            return responseDTO;
            }).toList();

            return questionDTO;
        }).toList());

        return dto;
    }

    
}

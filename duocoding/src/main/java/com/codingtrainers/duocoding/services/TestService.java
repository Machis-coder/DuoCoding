package com.codingtrainers.duocoding.services;


import com.codingtrainers.duocoding.dto.input.TestRequestDTO;
import com.codingtrainers.duocoding.dto.output.QuestionResponseDTO;
import com.codingtrainers.duocoding.dto.output.TestResponseDTO;
import com.codingtrainers.duocoding.entities.Question;
import com.codingtrainers.duocoding.entities.Subject;
import com.codingtrainers.duocoding.entities.Test;

import com.codingtrainers.duocoding.entities.TestQuestion;
import com.codingtrainers.duocoding.repositories.SubjectRepository;
import com.codingtrainers.duocoding.repositories.TestQuestionRepository;
import com.codingtrainers.duocoding.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TestQuestionRepository testQuestionRepository;

    public List<TestResponseDTO> getAllTestDTOs() {
        List<Test> tests = testRepository.findAll();
        List<TestResponseDTO> dtos = new ArrayList<>();

        for (Test test : tests) {
            List<QuestionResponseDTO> questions = getQuestionsForTest(test.getId());
            dtos.add(new TestResponseDTO(
                    test.getId(),
                    test.getName(),
                    test.getDescription(),
                    test.getActive(),
                    test.getSubject() != null ? test.getSubject().getId() : null,
                    questions
            ));
        }
        return dtos;
    }

    public Optional<TestResponseDTO> getTestDTOById(Long id) {
        return testRepository.findById(id).map(test -> {
            List<QuestionResponseDTO> questions = getQuestionsForTest(test.getId());
            return new TestResponseDTO(
                    test.getId(),
                    test.getName(),
                    test.getDescription(),
                    test.getActive(),
                    test.getSubject() != null ? test.getSubject().getId() : null,
                    questions
            );
        });
    }

    private List<QuestionResponseDTO> getQuestionsForTest(Long testId) {
        List<TestQuestion> testQuestions = testQuestionRepository.findByTestId(testId);
        List<QuestionResponseDTO> questions = new ArrayList<>();

        for (TestQuestion tq : testQuestions) {
            Question q = tq.getQuestion();

            QuestionResponseDTO dto = new QuestionResponseDTO();
            dto.setId(q.getId());
            dto.setAnswer(q.getAnswer());
            dto.setType(q.getType());
            dto.setDescription(q.getDescription());
            dto.setResponses(null);

            questions.add(dto);
        }

        return questions;
    }


    public TestResponseDTO createTest(TestRequestDTO dto) {
        Test test = new Test();
        test.setName(dto.getName());
        test.setDescription(dto.getDescription());
        test.setActive(dto.getActive());

        Subject subject = subjectRepository.findByIdAndActiveTrue(dto.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + dto.getSubjectId()));

        test.setSubject(subject);

        Test savedTest = testRepository.save(test);

        return new TestResponseDTO(
                savedTest.getId(),
                savedTest.getName(),
                savedTest.getDescription(),
                savedTest.getActive(),
                savedTest.getSubject() != null ? savedTest.getSubject().getId() : null,
                new ArrayList<>()
        );
    }

    public Optional<TestResponseDTO> updateTest(Long id, TestRequestDTO dto) {
        return testRepository.findActiveById(id).map(test -> {
            test.setName(dto.getName());
            test.setDescription(dto.getDescription());
            test.setActive(dto.getActive());

            Subject subject = subjectRepository.findByIdAndActiveTrue(dto.getSubjectId())
                    .orElseThrow(() -> new RuntimeException("Subject not found with id: " + dto.getSubjectId()));

            test.setSubject(subject);

            Test updated = testRepository.save(test);

            List<QuestionResponseDTO> questions = getQuestionsForTest(updated.getId());

            return new TestResponseDTO(
                    updated.getId(),
                    updated.getName(),
                    updated.getDescription(),
                    updated.getActive(),
                    updated.getSubject() != null ? updated.getSubject().getId() : null,
                    questions
            );
        });
    }

    public void deleteTestById(Long id) {
        Test test = testRepository.findActiveById(id)
                .orElseThrow(() -> new RuntimeException("Test not found with id: " + id));
        test.setActive(false);
        testRepository.save(test);
    }
    public void activateTestById(Long id) {
        Test test = testRepository.findFalseById(id)
                .orElseThrow(() -> new RuntimeException("Test not found with id: " + id));
        test.setActive(true);
        testRepository.save(test);
    }
}


   /* public TestExecutionFullDTO getExamStructure(Long testId) {

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
    }*/

    


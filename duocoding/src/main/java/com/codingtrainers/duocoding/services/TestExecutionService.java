package com.codingtrainers.duocoding.services;

import com.codingtrainers.duocoding.dto.input.NotesFromTeacherRequestDTO;
import com.codingtrainers.duocoding.dto.input.TestExecutionRequestDTO;
import com.codingtrainers.duocoding.dto.input.TestExecutionResponseRequestDTO;
import com.codingtrainers.duocoding.dto.output.QuestionDTO;
import com.codingtrainers.duocoding.dto.output.ResponseDTO;
import com.codingtrainers.duocoding.dto.output.TestExecutionDTO;
import com.codingtrainers.duocoding.dto.output.TestExecutionResponseDTO;
import com.codingtrainers.duocoding.dtos.QuestionFullDTO;
import com.codingtrainers.duocoding.dtos.TestExecutionFullDTO;
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

    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private TestService testService;

    @Autowired
    private QuestionService questionService;


    public List<TestExecution> getTestExecutions() {
        return testExecutionRepository.findAll();
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

    public TestExecutionFullDTO getTestExecution(Long testExecutionId) {

        TestExecution testExecution = testExecutionRepository.findById(testExecutionId).orElseThrow(()->new EntityNotFoundException("Test Execution not found"));

        Test test = testExecution.getTest();

        TestExecutionFullDTO dto = new TestExecutionFullDTO();
        dto.setTestId(test.getId());
        dto.setTestTitle(test.getName());
        //TODO dto.setTestDescription(test.getDescription());

        List<TestQuestion> testQuestions = questionService.findTestQuestionsByTestId(test.getId());
        List<TestExecutionResponse> testExecutionResponses = testExecutionResponseRepository.findAllByTestExecutionId(testExecutionId);
        List<Response> responses = questionService.findAllResponsesByQuestionIds(testQuestions.stream().map(it -> it.getQuestion().getId()).toList());
        Map<Long, List<Response>> responseMap = new HashMap<>();

        responses.stream().forEach(it -> {
            List<Response> questionResponses = responseMap.get(it.getQuestion().getId());
            if (questionResponses == null) {
                questionResponses = new ArrayList<>();
                questionResponses.add(it);
                responseMap.put(it.getQuestion().getId(), questionResponses);
            } else {
                questionResponses.add(it);
            }
        });

        dto.setQuestions(testExecutionResponses.stream().map(tq -> {

            com.codingtrainers.duocoding.entities.Question question = tq.getQuestion();

            QuestionFullDTO questionDTO = new QuestionFullDTO();
            questionDTO.setQuestionId(question.getId());
            questionDTO.setDescription(question.getDescription());
            questionDTO.setAnswer(tq.getAnswer());
            questionDTO.setCorrect(tq.getCorrect());
            questionDTO.setResponses(responseMap.get(questionDTO.getQuestionId()));

            return questionDTO;
        }).toList());

        return dto;
    }

//todo Arreglar este método

    public TestExecutionDTO gesTestExecutionById(Long testExecutionId) {
        TestExecution execution = testExecutionRepository.findById(testExecutionId)
                .orElseThrow(() -> new RuntimeException("TestExecution not found"));

        List<TestExecutionResponse> responseList =
                testExecutionResponseRepository.findByTestExecutionId(testExecutionId);


        Map<Long, List<Response>> allResponsesByQuestion = new HashMap<>();
        List<TestExecutionResponseDTO> executionResponseDTOs = new ArrayList<>();

        for (TestExecutionResponse execResponse : responseList) {
            Question question = execResponse.getQuestion();


            allResponsesByQuestion.computeIfAbsent(question.getId(),
                    id -> responseRepository.findByQuestionId(id));


            TestExecutionResponseDTO respDTO = new TestExecutionResponseDTO();
            respDTO.setId(execResponse.getId());
            respDTO.setQuestionId(question.getId());
            respDTO.setAnswer(execResponse.getAnswer());
            respDTO.setCorrect(execResponse.getCorrect());
            respDTO.setNotes(execResponse.getNotes());

            executionResponseDTOs.add(respDTO);
        }


        List<QuestionDTO> questionDTOList = responseList.stream().map(execResp -> {
            Question question = execResp.getQuestion();
            List<ResponseDTO> responseDTOs = allResponsesByQuestion.getOrDefault(question.getId(), new ArrayList<>())
                    .stream()
                    .map(resp -> new ResponseDTO(resp.getId(), resp.getDescription(), resp.getOrder()))
                    .collect(Collectors.toList());

            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setDescription(question.getDescription());
            questionDTO.setType(question.getType());
            questionDTO.setAnswer(execResp.getAnswer()); // respuesta seleccionada
            questionDTO.setResponses(responseDTOs);

            return questionDTO;
        }).collect(Collectors.toList());


        TestExecutionDTO dto = new TestExecutionDTO();
        dto.setId(execution.getId());
        dto.setTestId(execution.getTest().getId());
        dto.setUserId(execution.getUser().getId());
        dto.setDate(Date.valueOf(execution.getDate()).toLocalDate());
        dto.setStartTime(execution.getStartTime());
        dto.setEndTime(execution.getFinishTime());
        dto.setResult(execution.getResult());
        dto.setNotes(execution.getNotes());
        dto.setExecutionResponsesList(executionResponseDTOs);

        return dto;
    }

}

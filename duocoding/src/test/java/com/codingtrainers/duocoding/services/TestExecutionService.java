package com.codingtrainers.duocoding.services;

import com.codingtrainers.duocoding.repositories.QuestionRepository;
import com.codingtrainers.duocoding.repositories.TestExecutionRepository;
import com.codingtrainers.duocoding.repositories.TestExecutionResponseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestExecutionService {

    @Mock
    private TestExecutionRepository testExecutionRepository;

    @InjectMocks
    private TestExecutionService testExecutionService;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private TestExecutionResponseRepository testExecutionResponseRepository;

    @Test
    void executeTest_savesResponsesAndUpdatesResult(){

    }
}

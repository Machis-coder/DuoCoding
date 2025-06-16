package com.codingtrainers.duocoding.repositories;

import com.codingtrainers.duocoding.entities.Question;
import com.codingtrainers.duocoding.entities.Response;
import com.codingtrainers.duocoding.entities.TestExecution;
import com.codingtrainers.duocoding.entities.TestExecutionResponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestExecutionResponseRepository extends CrudRepository<TestExecutionResponse, Long> {

    List<TestExecutionResponse> findByTestExecution(TestExecution testExecution);


    Optional<TestExecutionResponse> findByTestExecutionAndQuestion(TestExecution testExecution, Question question);

    void deleteByTestExecution(TestExecution testExecution);
}

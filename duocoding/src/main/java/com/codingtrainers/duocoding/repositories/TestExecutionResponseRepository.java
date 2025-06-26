package com.codingtrainers.duocoding.repositories;

import com.codingtrainers.duocoding.entities.Question;
import com.codingtrainers.duocoding.entities.Response;
import com.codingtrainers.duocoding.entities.TestExecution;
import com.codingtrainers.duocoding.entities.TestExecutionResponse;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestExecutionResponseRepository extends CrudRepository<TestExecutionResponse, Long> {

    @Query("SELECT ter FROM TestExecutionResponse ter WHERE ter.testExecution = :testExecution AND ter.active = true")
    List<TestExecutionResponse> findActiveByTestExecution(@Param("testExecution") TestExecution testExecution);

    @Query("SELECT ter FROM TestExecutionResponse ter WHERE ter.testExecution = :testExecution AND ter.question = :question AND ter.active = true")
    Optional<TestExecutionResponse> findActiveByTestExecutionAndQuestion(@Param("testExecution") TestExecution testExecution, @Param("question") Question question);

    @Modifying
    @Query("UPDATE TestExecutionResponse ter SET ter.active = false WHERE ter.testExecution = :testExecution")
    void softDeleteByTestExecution(@Param("testExecution") TestExecution testExecution);

    @Query("SELECT ter FROM TestExecutionResponse ter WHERE ter.testExecution.id = :testExecutionId AND ter.active = true")
    List<TestExecutionResponse> findActiveAllByTestExecutionId(@Param("testExecutionId") Long testExecutionId);

    @Query("SELECT ter FROM TestExecutionResponse ter WHERE ter.testExecution.id = :testExecutionId AND ter.active = true")
    List<TestExecutionResponse> findActiveByTestExecutionId(@Param("testExecutionId") Long testExecutionId);

    @Query("SELECT ter FROM TestExecutionResponse ter WHERE ter.id = :id AND ter.active = true")
    Optional<TestExecutionResponse> findActiveById(@Param("id") Long id);



}

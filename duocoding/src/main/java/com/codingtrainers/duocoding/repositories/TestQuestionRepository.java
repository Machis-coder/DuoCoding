package com.codingtrainers.duocoding.repositories;


import com.codingtrainers.duocoding.entities.TestQuestion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TestQuestionRepository extends JpaRepository<TestQuestion,Long> {



    @Query("SELECT tq FROM TestQuestion tq WHERE tq.test.id = :testId AND tq.test.active = true AND tq.question.active = true")
    List<TestQuestion> findByTestId(@Param("testId") Long testId);

}

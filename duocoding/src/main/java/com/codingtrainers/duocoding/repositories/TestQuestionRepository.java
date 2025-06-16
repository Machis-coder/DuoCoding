package com.codingtrainers.duocoding.repositories;


import com.codingtrainers.duocoding.entities.TestQuestion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestQuestionRepository extends JpaRepository<TestQuestion,Long> {
    List<TestQuestion> findByTestId(Long testId);
}

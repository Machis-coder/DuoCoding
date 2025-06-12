package com.codingtrainers.duocoding.repositories;

import com.codingtrainers.duocoding.entities.Exercise;
import com.codingtrainers.duocoding.entities.TestExecution;
import com.codingtrainers.duocoding.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestExecutionRepository extends JpaRepository<TestExecution, Long> {

    List<TestExecution> findByUser(User user);

    List<TestExecution> findByTest(Exercise exercise);
}

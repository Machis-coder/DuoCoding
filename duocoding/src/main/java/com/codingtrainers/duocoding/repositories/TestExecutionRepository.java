package com.codingtrainers.duocoding.repositories;

import com.codingtrainers.duocoding.entities.Test;
import com.codingtrainers.duocoding.entities.TestExecution;
import com.codingtrainers.duocoding.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestExecutionRepository extends JpaRepository<TestExecution, Long> {

    List<TestExecution> findByUser(User user);

    List<TestExecution> findByTest(Test test);

    List<TestExecution> findByUserId(Long userId);

    Optional<TestExecution> findById(Long id);
}

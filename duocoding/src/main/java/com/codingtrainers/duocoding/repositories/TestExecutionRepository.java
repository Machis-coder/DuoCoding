package com.codingtrainers.duocoding.repositories;

import com.codingtrainers.duocoding.entities.Test;
import com.codingtrainers.duocoding.entities.TestExecution;
import com.codingtrainers.duocoding.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestExecutionRepository extends JpaRepository<TestExecution, Long> {

    @Query("SELECT te FROM TestExecution te WHERE te.user = :user AND te.active = true")
    List<TestExecution> findActiveByUser(@Param("user") User user);

    @Query("SELECT te FROM TestExecution te WHERE te.test = :test AND te.active = true")
    List<TestExecution> findActiveByTest(@Param("test") Test test);

    @Query("SELECT te FROM TestExecution te WHERE te.user.id = :userId AND te.active = true")
    List<TestExecution> findActiveByUserId(@Param("userId") Long userId);

    @Query("SELECT te FROM TestExecution te WHERE te.id = :id AND te.active = true")
    Optional<TestExecution> findActiveById(@Param("id") Long id);

    List<TestExecution> findAllByActiveTrue();
    List<TestExecution> findAllByActiveFalse();
}

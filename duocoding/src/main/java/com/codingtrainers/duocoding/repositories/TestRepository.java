package com.codingtrainers.duocoding.repositories;

import com.codingtrainers.duocoding.entities.Subject;
import com.codingtrainers.duocoding.entities.Test;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

    @Query("SELECT t.subject.id FROM Test t WHERE t.id = :testId AND t.active = true")
    Long findActiveSubjectIdByTestId(@Param("testId") Long testId);

    @Query("SELECT t FROM Test t WHERE t.id = :testId AND t.active = true")
    Optional<Test> findActiveByTestId(@Param("testId") Long testId);

    @Query("SELECT t FROM Test t WHERE t.active = true")
    List<Test> findAllActive();

    @Query("SELECT t FROM Test t WHERE t.id = :id AND t.active = true")
    Optional<Test> findActiveById(@Param("id") Long id);

    @Query("SELECT t FROM Test t WHERE t.id = :id AND t.active = false")
    Optional<Test> findFalseById(@Param("id") Long id);



}


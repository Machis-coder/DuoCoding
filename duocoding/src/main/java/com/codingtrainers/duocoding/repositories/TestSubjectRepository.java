package com.codingtrainers.duocoding.repositories;

import com.codingtrainers.duocoding.entities.TestSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestSubjectRepository extends JpaRepository<TestSubject, Long> {
}

package com.codingtrainers.duocoding.repositories;

import com.codingtrainers.duocoding.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Exercise, Long> {
}


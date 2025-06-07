package com.codingtrainers.duocoding.repositories;

import com.codingtrainers.duocoding.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {


}


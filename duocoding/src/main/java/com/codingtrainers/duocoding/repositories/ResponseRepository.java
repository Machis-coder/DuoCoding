package com.codingtrainers.duocoding.repositories;

import com.codingtrainers.duocoding.entities.Question;
import com.codingtrainers.duocoding.entities.QuestionType;
import com.codingtrainers.duocoding.entities.Response;
import jakarta.annotation.PostConstruct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {

    List<Response> findByQuestionId(Long questionId);

    @Query("select r from Response r where question.id in (:questionIds)")
    List<Response> findAllByQuestionIdIn(@Param("questionIds") List<Long> questionIds);
}

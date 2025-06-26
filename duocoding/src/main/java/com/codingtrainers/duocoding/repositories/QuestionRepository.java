package com.codingtrainers.duocoding.repositories;

import com.codingtrainers.duocoding.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT q FROM Question q WHERE q.id IN :ids AND q.active = true")
    List<Question> findAllActiveByIdIn(@Param("ids") List<Long> ids);

    List<Question> findAllByActiveTrue();
    Optional<Question> findByIdAndActiveTrue(Long id);
}


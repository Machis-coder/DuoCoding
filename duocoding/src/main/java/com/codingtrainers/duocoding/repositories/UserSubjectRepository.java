package com.codingtrainers.duocoding.repositories;

import com.codingtrainers.duocoding.entities.UserSubject;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserSubjectRepository extends JpaRepository<UserSubject, Long> {

    Optional<UserSubject> findByUserIdAndSubjectIdAndActiveTrue(Long userId, Long subjectId);
   
    Optional<UserSubject> findByUserIdAndSubjectId(Long userId, Long subjectId);

    List<UserSubject> findByUserIdAndActiveTrue(Long userId);

    List<UserSubject> findBySubjectIdAndActiveTrue(Long subjectId);


}

package com.codingtrainers.duocoding.repositories;
import com.codingtrainers.duocoding.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {




}

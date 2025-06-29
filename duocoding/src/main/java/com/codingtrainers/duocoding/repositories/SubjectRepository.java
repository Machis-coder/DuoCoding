package com.codingtrainers.duocoding.repositories;
import com.codingtrainers.duocoding.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

Optional<Subject> findByIdAndActiveTrue(Long id);
List<Subject> findAllByActiveTrue();

}

package com.codingtrainers.duocoding.repositories;

import com.codingtrainers.duocoding.entities.Subject;
import com.codingtrainers.duocoding.entities.User;
import com.codingtrainers.duocoding.entities.UserSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserSubjectRepository extends JpaRepository<UserSubject, Long> {

    List<UserSubject> findByUser(User user);

    List<UserSubject> findBySubject(Subject subject);

    Optional<UserSubject> findByUserAndSubject(User user, Subject subject);

    void deleteByUserAndSubject(User user, Subject subject);


}

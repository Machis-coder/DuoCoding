package com.codingtrainers.duocoding.repositories;

import com.codingtrainers.duocoding.entities.Subject;
import com.codingtrainers.duocoding.entities.User;
import com.codingtrainers.duocoding.entities.UserSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserSubjectRepository extends JpaRepository<UserSubject, Long> {

    List<UserSubject> findByUser(User user);

    List<UserSubject> findBySubject(Subject subject);

    Optional<UserSubject> findByUserAndSubject(User user, Subject subject);

    void deleteByUserAndSubject(User user, Subject subject);

    boolean existsByUserUsernameAndSubjectId(String username, Long subjectId);
    @Query("SELECT us.subject.id FROM UserSubject us WHERE us.user.id = :userId")
    List<Long> findSubjectIdsByUserId(@Param("userId") Long userId);


}

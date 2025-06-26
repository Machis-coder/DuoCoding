package com.codingtrainers.duocoding.repositories;
import com.codingtrainers.duocoding.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByActiveFalse();

    List<User> findAllByActiveTrue();

    User save(User user);

    Optional<User> findByUsernameAndActiveTrue(String username);

    Optional<User> findByIdAndActiveTrue(Long id);

    List<User> findAllByActiveTrueAndUsernameOrEmail(String username, String email);

    @Query("SELECT u FROM User u WHERE u.name = :username AND u.password = :hashedPassword AND u.active = true")
    List<User> userLogin(@Param("username") String username, @Param("hashedPassword") String hashedPassword);

}

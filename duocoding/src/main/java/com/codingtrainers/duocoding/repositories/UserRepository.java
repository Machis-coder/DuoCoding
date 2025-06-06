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

        List<User> findAll();

        User save(User user);

        Optional<User> findById(Long id);

        List<User> findAllByUsernameOrEmail(String username, String email);

    @Query("SELECT u FROM User u WHERE u.name = :username AND u.password = :hashedPassword")
    List<User> userLogin(@Param("username") String username, @Param("hashedPassword") String hashedPassword);
}

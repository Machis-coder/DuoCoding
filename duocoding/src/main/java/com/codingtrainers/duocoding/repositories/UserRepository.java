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
    @Query("SELECT u FROM User u WHERE u.active = true and u.role != 'SUPER'")
    List<User> findAllByActiveTrueExceptSuper();
    @Query("SELECT u FROM User u WHERE u.active = false and u.role != 'SUPER'")
    List<User> findAllByActiveFalse();

    User save(User user);

    Optional<User> findByUsernameAndActiveTrue(String username);

    Optional<User> findByIdAndActiveTrue(Long id);

    List<User> findAllByActiveTrueAndUsernameOrEmail(String username, String email);

    @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = SHA2(:password, 256) AND u.active = true ")
    List<User> userLogin(@Param("username") String username, @Param("password") String password);

}

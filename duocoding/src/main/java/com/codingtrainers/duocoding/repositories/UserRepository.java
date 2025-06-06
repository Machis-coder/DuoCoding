package com.codingtrainers.duocoding.repositories;
import com.codingtrainers.duocoding.entities.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class UserRepository {

    private List<User> users;
    public UserRepository() {
        System.out.println(this.getClass().getName());
    }
    @PostConstruct
    public void init() {
        users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User(
                    i,
                    "User" + i,
                    "user" + i + "@example.com",
                    "password" + i,
                    "Surname" + i,
                    LocalDateTime.now().minusYears(20).plusDays(i),
                    "DNI" + i,
                    true,
                    (i % 2 == 0) ? "admin" : "user"
            );
            users.add(user);
        }
    }

    public List<User> findAll() {
        return users;
    }

}

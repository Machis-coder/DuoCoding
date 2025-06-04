package com.codingtrainers.duocoding.repositories;

import com.codingtrainers.duocoding.entities.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserRepository {

    private List<User> users;
    public UserRepository() {
        System.out.println(this.getClass().getName());
    }
    @PostConstruct
    private void init() {
        users = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User user = new User(i,"max"+i,i+"@hotmail.com","1234falsa"+i );
            users.add(user);
        }
    }

    public List<User> findAll() {
        return users;
    }

}

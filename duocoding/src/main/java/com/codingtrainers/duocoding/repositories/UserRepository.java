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

    }

    public List<User> findAll() {
        return users;
    }

}

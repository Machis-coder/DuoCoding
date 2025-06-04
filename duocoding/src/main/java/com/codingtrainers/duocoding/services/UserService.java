package com.codingtrainers.duocoding.services;


import com.codingtrainers.duocoding.entities.User;
import com.codingtrainers.duocoding.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public UserService() {
        System.out.println(this.getClass().getName());

    }
}

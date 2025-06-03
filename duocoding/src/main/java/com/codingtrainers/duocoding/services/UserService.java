package com.codingtrainers.DuoCoding.services;

import com.codingtrainers.DuoCoding.entities.User;
import com.codingtrainers.DuoCoding.repositories.UserRepository;
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

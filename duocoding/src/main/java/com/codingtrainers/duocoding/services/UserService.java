package com.codingtrainers.duocoding.services;


import com.codingtrainers.duocoding.entities.User;
import com.codingtrainers.duocoding.repositories.UserRepository;
import com.codingtrainers.duocoding.utils.HashUtils;
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

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new RuntimeException("User Not Found"));
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public void create(User user) {
        String password = user.getPassword();
        password = HashUtils.sha256(password);
        user.setPassword(password);
         userRepository.save(user);
    }

}

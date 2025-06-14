package com.codingtrainers.duocoding.controllers;

import com.codingtrainers.duocoding.entities.User;
import com.codingtrainers.duocoding.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Long id){
        return userService.getById(id);
    }


    @PutMapping("/")
    public void update(@RequestBody User user){
        userService.update(user);
    }






}

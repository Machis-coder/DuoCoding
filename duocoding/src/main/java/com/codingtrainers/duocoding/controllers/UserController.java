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

    @GetMapping("/all")
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/id/{id}")
    public User findById(@RequestParam("id") Long id){
        return userService.getById(id);
    }

    @PostMapping("/create")
    public void create(@RequestBody User user){
        userService.create(user);
    }

    @PutMapping("/update")
    public void update(@RequestBody User user){
        userService.update(user);
    }






}

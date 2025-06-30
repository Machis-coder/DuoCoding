package com.codingtrainers.duocoding.controllers;

import com.codingtrainers.duocoding.dto.input.LoginRequestDTO;
import com.codingtrainers.duocoding.entities.User;
import com.codingtrainers.duocoding.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin("*")
public class LoginController {
    @Autowired
    private UserService userService;


    @PostMapping("/signin")
    public ResponseEntity<User> doLogin(@RequestBody LoginRequestDTO login) {
        try {
            User user = userService.findUserByUsernameAndPassword(login.getUsername(), login.getPassword());
            return ResponseEntity.ok(user);
        } catch (Exception lex) {
            return ResponseEntity.notFound().build();
        }
    }
}

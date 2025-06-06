package com.codingtrainers.duocoding.controllers;

import com.codingtrainers.duocoding.entities.Test;
import com.codingtrainers.duocoding.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tests")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/")
    public List<Test> getAllTests() {
        return testService.getAll();
    }

    @GetMapping("/{id}")
    public Test getTestById(@PathVariable int id) {
        return testService.getById(id);
    }
}

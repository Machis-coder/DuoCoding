package com.codingtrainers.duocoding.controllers;
import com.codingtrainers.duocoding.entities.Subject;
import com.codingtrainers.duocoding.entities.User;
import com.codingtrainers.duocoding.services.SubjectService;
import com.codingtrainers.duocoding.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/")
    public List<Subject> getAll() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/{id}")
    public Subject findById(@RequestParam("id") Long id) {
        return subjectService.getById(id);
    }

    @PostMapping("/")
    public void create(@RequestBody Subject subject) {
        subjectService.createSubject(subject);
    }

    @PutMapping
    public void update(@RequestBody Subject subject) {
        subjectService.updateSubject(subject);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        subjectService.deleteTestQuestion(id);
    }
}


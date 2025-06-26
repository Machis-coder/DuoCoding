package com.codingtrainers.duocoding.controllers;
import com.codingtrainers.duocoding.entities.Subject;
import com.codingtrainers.duocoding.entities.User;
import com.codingtrainers.duocoding.services.SubjectService;
import com.codingtrainers.duocoding.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/")
    public List<Subject> getAll() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> findById(@PathVariable("id") Long id) {
        try {
            Optional<Subject> subjectOpt = subjectService.getById(id);
            if (subjectOpt.isPresent()) {
                return ResponseEntity.ok(subjectOpt.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/")
    public void create(@RequestBody Subject subject) {
        subjectService.createSubject(subject);
    }

    @PutMapping("/")
    public void update(@RequestBody Subject subject) {
        subjectService.updateSubject(subject);
    }
    @PutMapping("/{id}/delete")
    public void delete(@PathVariable Long id) {
        subjectService.deleteTestSubject(id);
    }
}


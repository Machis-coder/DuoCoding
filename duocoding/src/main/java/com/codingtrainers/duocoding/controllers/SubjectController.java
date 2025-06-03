package com.codingtrainers.duocoding.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @GetMapping("/")
    public List<String> getAll() {
        return List.of("Maths", "Language","English","Biography" );

    }
}

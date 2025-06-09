//package com.codingtrainers.duocoding.controllers;
//import com.codingtrainers.duocoding.entities.Subject;
//import com.codingtrainers.duocoding.services.SubjectService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import java.util.List;

//@RestController
//@RequestMapping("/subject")
//public class SubjectController {
//
//    @Autowired
//    private SubjectService subjectService;
//
//    @GetMapping("/")
//    public List<Subject> getSubjects(){
//        return subjectService.getAll();
//    }
//
//    @GetMapping("/{id}")
//    public Subject getSubjectById(@PathVariable int id){
//        return subjectService.findById(id);
//    }
//
//}

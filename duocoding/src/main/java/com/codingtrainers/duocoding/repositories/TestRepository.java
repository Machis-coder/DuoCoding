package com.codingtrainers.duocoding.repositories;

import com.codingtrainers.duocoding.entities.Subject;
import com.codingtrainers.duocoding.entities.Test;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestRepository {

//    private List<Test> tests;
//
//    @Autowired
//    private SubjectRepository subjectRepository;
//
//    @PostConstruct
//    public void init() {
//        tests = new ArrayList<>();
//
//        Subject java = subjectRepository.findById(1);
//        Subject databases = subjectRepository.findById(2);
//        Subject frontend = subjectRepository.findById(3);
//        Subject devops = subjectRepository.findById(4);
//
//        tests.add(new Test(1, "Java Basics Test", "Test sobre conceptos básicos de Java", java));
//        tests.add(new Test(2, "SQL Queries Test", "Test sobre consultas SQL", databases));
//        tests.add(new Test(3, "Angular Test", "Test sobre fundamentos de Angular", frontend));
//        tests.add(new Test(4, "Deploy Test", "Test sobre despliegue", devops));
//    }
//
//    public List<Test> findAll() {
//        return tests;
//    }
//
//    public Test findById(int id) {
//        for (Test test : tests) {
//            if (test.getId() == id) {
//                return test;
//            }
//        }
//        return null;
//    }
}

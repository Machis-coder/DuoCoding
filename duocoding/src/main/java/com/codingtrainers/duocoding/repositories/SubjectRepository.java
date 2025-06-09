package com.codingtrainers.duocoding.repositories;

import com.codingtrainers.duocoding.entities.Subject;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Service
//public class SubjectRepository {
//
//    private List<Subject> subjects;
//
//    @PostConstruct
//    public void init() {
//        subjects = new ArrayList<>();
//        subjects.add(new Subject("Java", 1, "Java Subject"));
//        subjects.add(new Subject("Databases", 2, "Databases Subject"));
//        subjects.add(new Subject("Frontend", 3, "Frontend Subject"));
//        subjects.add(new Subject("Devops", 4, "Devops Subject"));
//    }
//
//    public List<Subject> getSubjects() {
//        return subjects;
//    }
//
//    public Subject findById(int id) {
//        for (Subject subject : subjects) {
//            if (subject.getId() == id) {
//                return subject;
//            }
//        }
//        return null;
//    }
//
//}

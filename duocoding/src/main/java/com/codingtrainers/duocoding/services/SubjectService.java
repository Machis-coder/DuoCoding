package com.codingtrainers.duocoding.services;

import com.codingtrainers.duocoding.entities.Subject;
import com.codingtrainers.duocoding.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject Not Found"));
    }

    public Subject createSubject(Subject subject) {
        if (subject == null) {
            throw new IllegalArgumentException("Subject cannot be null");
        }
        return subjectRepository.save(subject);
    }

    public Subject updateSubject(Subject subject) {
        if (subject == null) {
            throw new IllegalArgumentException("Subject cannot be null");
        }
        return subjectRepository.save(subject);
    }

    public String deleteTestQuestion(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (!subjectRepository.existsById(id)) {
            throw new RuntimeException("Subject not found");
        }
        subjectRepository.deleteById(id);
        return "Subject removed successfully";
    }
}

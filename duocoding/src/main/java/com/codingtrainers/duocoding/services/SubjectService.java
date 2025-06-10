package com.codingtrainers.duocoding.services;

import com.codingtrainers.duocoding.entities.Subject;
import com.codingtrainers.duocoding.entities.User;
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
        return subjectRepository.findById(id).orElseThrow(() -> new RuntimeException("Subject Not Found"));
    }

    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Subject updateSubject(Subject subject) {
        return subjectRepository.save(subject);
    }
    public String deleteTestQuestion(Long id){
        if(!subjectRepository.existsById(id)){
            throw new RuntimeException("Subject not found");
        }
        subjectRepository.deleteById(id);
        return "Subject removed successfully";
    }
}

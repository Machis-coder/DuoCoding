package com.codingtrainers.duocoding.services;

import com.codingtrainers.duocoding.entities.Subject;
import com.codingtrainers.duocoding.repositories.SubjectRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAllByActiveTrue();
    }
    public Optional<Subject> getById(Long id) {

        return subjectRepository.findByIdAndActiveTrue(id);
    }

    public void createSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    public void updateSubject(Subject subject) {
        subjectRepository.save(subject);
    }
    public void deleteTestSubject(Long id){
        if(!subjectRepository.existsById(id)){
            throw new RuntimeException("Subject not found");
        }
        Subject subject = subjectRepository.findById(id).get();
        subject.setActive(false);
        subjectRepository.save(subject);
    }
}

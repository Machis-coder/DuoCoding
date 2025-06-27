package com.codingtrainers.duocoding.services;


import com.codingtrainers.duocoding.dto.output.UserSubjectResponseDTO;

import com.codingtrainers.duocoding.entities.Subject;
import com.codingtrainers.duocoding.entities.User;
import com.codingtrainers.duocoding.entities.UserSubject;
import com.codingtrainers.duocoding.repositories.SubjectRepository;
import com.codingtrainers.duocoding.repositories.UserRepository;
import com.codingtrainers.duocoding.repositories.UserSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserSubjectService {

    @Autowired
    private UserSubjectRepository userSubjectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    public List<UserSubjectResponseDTO> getSubjectsByUser(Long userId) {
        return userSubjectRepository.findByUserIdAndActiveTrue(userId).stream()
                .map(UserSubjectResponseDTO::new)
                .toList();
    }

    public List<UserSubjectResponseDTO> getUsersBySubject(Long subjectId) {
        return userSubjectRepository.findBySubjectIdAndActiveTrue(subjectId).stream()
                .map(UserSubjectResponseDTO::new)
                .toList();
    }

    public UserSubject assignUserToSubject(Long userId, Long subjectId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("User not found with ID: " + userId));
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() ->
                new RuntimeException("Subject not found with ID: " + subjectId));


        Optional<UserSubject> exists = userSubjectRepository.findByUserIdAndSubjectIdAndActiveTrue(userId, subjectId);
            if (exists.isPresent()) {
             throw new RuntimeException("User is already assigned to the subject.");
        }


        Optional<UserSubject> isActive = userSubjectRepository.findByUserIdAndSubjectId(userId, subjectId);
            if (isActive.isPresent()) {
                UserSubject reusing = isActive.get();
                reusing.setActive(false);
                    return userSubjectRepository.save(reusing);
        }

        UserSubject us = new UserSubject();
            us.setUser(user);
            us.setSubject(subject);
            us.setActive(true);

         return userSubjectRepository.save(us);
    }

    public UserSubject reactivateRelation(Long userId, Long subjectId) {
        UserSubject us = userSubjectRepository.findByUserIdAndSubjectId(userId, subjectId)
                .orElseThrow(() -> new RuntimeException("Relation not found."));
        us.setActive(true);
            return userSubjectRepository.save(us);
    }

    public UserSubject deleteRelation(Long userId, Long subjectId) {
        UserSubject us = userSubjectRepository.findByUserIdAndSubjectIdAndActiveTrue(userId, subjectId)
                .orElseThrow(() -> new RuntimeException("Active relation not found."));
        us.setActive(false);
            return userSubjectRepository.save(us);
    }
}

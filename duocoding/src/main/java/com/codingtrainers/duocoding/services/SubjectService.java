package com.codingtrainers.duocoding.services;

import com.codingtrainers.duocoding.dto.input.SubjectRequestDTO;
import com.codingtrainers.duocoding.dto.output.SubjectResponseDTO;
import com.codingtrainers.duocoding.dto.output.UserResponseDTO;
import com.codingtrainers.duocoding.entities.Subject;
import com.codingtrainers.duocoding.repositories.SubjectRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public List<SubjectResponseDTO> getAll() {
        return subjectRepository.findAllByActiveTrue()
                .stream()
                .map(SubjectResponseDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<SubjectResponseDTO> getById(Long id) {
        return subjectRepository.findByIdAndActiveTrue(id)
                .map(SubjectResponseDTO::new);
    }

    public Optional<SubjectResponseDTO> findByName(String name) {
        return subjectRepository.findByNameAndActiveTrue(name)
                .map(SubjectResponseDTO::new);
    }

    public void createSubject(SubjectRequestDTO dto) {
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new IllegalArgumentException("Subject name is required");
        }

        Subject subject = new Subject();
        subject.setName(dto.getName());
        subject.setDescription(dto.getDescription());
        subject.setActive(true);

        subjectRepository.save(subject);
    }

    public void updateSubject(Long id, SubjectRequestDTO dto) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found"));

        subject.setName(dto.getName());
        subject.setDescription(dto.getDescription());

        subjectRepository.save(subject);
    }

    public void deleteSubject(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found"));

        subject.setActive(false);
        subjectRepository.save(subject);
    }

    public void activateSubject(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found"));

        subject.setActive(true);
        subjectRepository.save(subject);
    }

    public List<SubjectResponseDTO> getInactiveSubjects(){
        return subjectRepository.findAllByActiveFalse()
                .stream()
                .map(SubjectResponseDTO::new)
                .collect(Collectors.toList());
    }

}

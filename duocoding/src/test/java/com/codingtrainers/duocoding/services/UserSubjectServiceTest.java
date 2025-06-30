package com.codingtrainers.duocoding.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.Collections;

import com.codingtrainers.duocoding.dto.output.UserSubjectResponseDTO;
import com.codingtrainers.duocoding.entities.Subject;
import com.codingtrainers.duocoding.entities.User;
import com.codingtrainers.duocoding.entities.UserSubject;
import com.codingtrainers.duocoding.repositories.SubjectRepository;
import com.codingtrainers.duocoding.repositories.UserRepository;
import com.codingtrainers.duocoding.repositories.UserSubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class UserSubjectServiceTest {

    @Mock
    private UserSubjectRepository userSubjectRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private UserSubjectService userSubjectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSubjectsByUser_ReturnsSubjects() {
        Long userId = 1L;
        UserSubject userSubject = new UserSubject();
        when(userSubjectRepository.findByUserIdAndActiveTrue(userId))
                .thenReturn(List.of(userSubject));

        List<UserSubjectResponseDTO> result = userSubjectService.getSubjectsByUser(userId);

        assertEquals(1, result.size());
    }

    @Test
    void testGetSubjectsByUser_ReturnsEmpty() {
        Long userId = 1L;
        when(userSubjectRepository.findByUserIdAndActiveTrue(userId))
                .thenReturn(Collections.emptyList());

        List<UserSubjectResponseDTO> result = userSubjectService.getSubjectsByUser(userId);

        assertTrue(result.isEmpty());
    }

    @Test
    void testAssignUserToSubject_Success() {
        Long userId = 1L;
        Long subjectId = 2L;

        User user = new User();
        Subject subject = new Subject();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(subjectRepository.findById(subjectId)).thenReturn(Optional.of(subject));
        when(userSubjectRepository.findByUserIdAndSubjectIdAndActiveTrue(userId, subjectId))
                .thenReturn(Optional.empty());
        when(userSubjectRepository.findByUserIdAndSubjectId(userId, subjectId))
                .thenReturn(Optional.empty());

        UserSubject saved = new UserSubject();
        when(userSubjectRepository.save(any(UserSubject.class))).thenReturn(saved);

        UserSubject result = userSubjectService.assignUserToSubject(userId, subjectId);

        assertNotNull(result);
    }

    @Test
    void testAssignUserToSubject_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            userSubjectService.assignUserToSubject(1L, 2L);
        });

        assertEquals("User not found with ID: 1", ex.getMessage());
    }

    @Test
    void testReactivateRelation_Success() {
        Long userId = 1L;
        Long subjectId = 2L;

        UserSubject relation = new UserSubject();
        relation.setActive(false);

        when(userSubjectRepository.findByUserIdAndSubjectId(userId, subjectId))
                .thenReturn(Optional.of(relation));

        when(userSubjectRepository.save(relation)).thenReturn(relation);

        UserSubject result = userSubjectService.reactivateRelation(userId, subjectId);

        assertTrue(result.getActive());
    }

    @Test
    void testDeleteRelation_Success() {
        Long userId = 1L;
        Long subjectId = 2L;

        UserSubject relation = new UserSubject();
        relation.setActive(true);

        when(userSubjectRepository.findByUserIdAndSubjectIdAndActiveTrue(userId, subjectId))
                .thenReturn(Optional.of(relation));

        when(userSubjectRepository.save(relation)).thenReturn(relation);

        UserSubject result = userSubjectService.deleteRelation(userId, subjectId);

        assertFalse(result.getActive());
    }

    @Test
    void testDeleteRelation_NotFound() {
        when(userSubjectRepository.findByUserIdAndSubjectIdAndActiveTrue(1L, 2L))
                .thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            userSubjectService.deleteRelation(1L, 2L);
        });

        assertEquals("Active relation not found.", ex.getMessage());
    }
}

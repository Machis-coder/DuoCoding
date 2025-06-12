package com.codingtrainers.duocoding.services;

import com.codingtrainers.duocoding.entities.User;
import com.codingtrainers.duocoding.repositories.UserRepository;
import com.codingtrainers.duocoding.utils.HashUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User sampleUser;

    @BeforeEach
    void setUp() {
        sampleUser = new User();
        sampleUser.setId(1L);
        sampleUser.setUsername("jdoe");
        sampleUser.setPassword("plaintext");
    }

    @Test
    void getAll_returnsListOfUsers() {
        when(userRepository.findAll()).thenReturn(List.of(sampleUser));

        List<User> result = userService.getAll();

        assertEquals(1, result.size());
        verify(userRepository).findAll();
    }

    @Test
    void getAll_returnsEmptyList() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        List<User> result = userService.getAll();

        assertTrue(result.isEmpty());
        verify(userRepository).findAll();
    }

    @Test
    void getById_returnsUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(sampleUser));

        User user = userService.getById(1L);

        assertEquals("jdoe", user.getUsername());
        verify(userRepository).findById(1L);
    }

    @Test
    void getById_throwsExceptionWhenNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.getById(99L);
        });

        assertEquals("User Not Found", exception.getMessage());
        verify(userRepository).findById(99L);
    }

    @Test
    void update_savesUser() {
        userService.update(sampleUser);

        verify(userRepository).save(sampleUser);
    }

    @Test
    void create_hashesPasswordAndSavesUser() {
        String hashed = HashUtils.sha256("plaintext");

        userService.create(sampleUser);

        assertEquals(hashed, sampleUser.getPassword());
        verify(userRepository).save(sampleUser);
    }

    @Test
    void create_withEmptyPasswordStillHashes() {
        sampleUser.setPassword("");
        String hashed = HashUtils.sha256("");

        userService.create(sampleUser);

        assertEquals(hashed, sampleUser.getPassword());
        verify(userRepository).save(sampleUser);
    }

    @Test
    void create_withNullPassword_throwsException() {
        sampleUser.setPassword(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
        userService.create(sampleUser);
    });

        assertEquals("User and password cannot be null", exception.getMessage());
}
    @Test
    void create_withNullUser_throwsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
        userService.create(null);
    });
        assertEquals("User and password cannot be null", exception.getMessage());
}
}

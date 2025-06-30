package com.codingtrainers.duocoding.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import com.codingtrainers.duocoding.dto.input.UserRequestDTO;
import com.codingtrainers.duocoding.dto.output.UserResponseDTO;
import com.codingtrainers.duocoding.entities.User;
import com.codingtrainers.duocoding.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;



@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User user;
    private UserRequestDTO userRequestDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setName("John");
        user.setSurname("Doe");
        user.setActive(true);
        user.setUsername("johndoe");
        user.setPassword("1234");

        userRequestDTO = new UserRequestDTO();
        userRequestDTO.setId(1L);
        userRequestDTO.setName("Updated");
        userRequestDTO.setSurname("User");
        userRequestDTO.setPassword("newpass");
        userRequestDTO.setUsername("updatedUser");
    }

    @Test
    void testGetAll() {
        when(userRepository.findAllByActiveTrue()).thenReturn(List.of(user));

        List<UserResponseDTO> result = userService.getAll();

        assertEquals(1, result.size());
        assertEquals(user.getUsername(), result.get(0).getUsername());
        verify(userRepository, times(1)).findAllByActiveTrue();
    }

    @Test
    void testGetByIdFound() {
        when(userRepository.findByIdAndActiveTrue(1L)).thenReturn(Optional.of(user));

        UserResponseDTO result = userService.getById(1L);

        assertEquals(user.getUsername(), result.getUsername());
        verify(userRepository).findByIdAndActiveTrue(1L);
    }

    @Test
    void testGetByIdNotFound() {
        when(userRepository.findByIdAndActiveTrue(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.getById(1L));
    }

    @Test
    void testUpdate() {
        when(userRepository.findByIdAndActiveTrue(1L)).thenReturn(Optional.of(user));

        userService.update(userRequestDTO);

        assertEquals(userRequestDTO.getName(), user.getName());
        assertEquals(userRequestDTO.getSurname(), user.getSurname());
        assertEquals(userRequestDTO.getUsername(), user.getUsername());
        verify(userRepository).save(user);
    }

    @Test
    void testFindByUsernameFound() {
        when(userRepository.findByUsernameAndActiveTrue("johndoe")).thenReturn(Optional.of(user));

        Optional<UserResponseDTO> result = userService.findByUsername("johndoe");

        assertTrue(result.isPresent());
        assertEquals("johndoe", result.get().getUsername());
    }

    @Test
    void testCreate() {
        userService.create(user);
        verify(userRepository).save(user);
        assertNotNull(user.getPassword()); // hashed
    }

    @Test
    void testDeleteUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.deleteUser(1L);

        assertFalse(user.isActive());
        verify(userRepository).save(user);
    }

    @Test
    void testActivateUser() {
        user.setActive(false);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.activateUser(1L);

        assertTrue(user.isActive());
        verify(userRepository).save(user);
    }

    @Test
    void testGetInactiveUsers() {
        user.setActive(false);
        when(userRepository.findAllByActiveFalse()).thenReturn(List.of(user));

        List<UserResponseDTO> result = userService.getInactiveUsers();

        assertEquals(1, result.size());
        assertEquals("johndoe", result.get(0).getUsername());
    }
}

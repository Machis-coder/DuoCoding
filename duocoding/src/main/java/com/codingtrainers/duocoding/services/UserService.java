package com.codingtrainers.duocoding.services;


import com.codingtrainers.duocoding.dto.input.UserRequestDTO;
import com.codingtrainers.duocoding.dto.output.UserResponseDTO;
import com.codingtrainers.duocoding.entities.User;
import com.codingtrainers.duocoding.repositories.UserRepository;
import com.codingtrainers.duocoding.utils.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserResponseDTO> getAll() {
        return userRepository.findAllByActiveTrue()
                .stream()
                .map(UserResponseDTO::new)
                .collect(Collectors.toList());
    }

    public UserResponseDTO getById(Long id) {
        User user = userRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        return new UserResponseDTO(user);
    }
    public void update(UserRequestDTO user) {
     User finalUser = userRepository.findByIdAndActiveTrue(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
     finalUser.setName(user.getName());
     finalUser.setSurname(user.getSurname());
     finalUser.setBirthday(user.getBirthday());
     finalUser.setDni(user.getDni());
     finalUser.setEmail(user.getEmail());
     finalUser.setUsername(user.getUsername());
     String password = user.getPassword();
     password = HashUtils.sha256(password);
     finalUser.setPassword(password);
        userRepository.save(finalUser);
    }

    public Optional<UserResponseDTO> findByUsername(String username) {
        return userRepository.findByUsernameAndActiveTrue(username)
                .map(UserResponseDTO::new);
    }

    public void create(User user) {
        String password = user.getPassword();
        password = HashUtils.sha256(password);
        user.setPassword(password);
         userRepository.save(user);
    }

    public void deleteUser (Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User Not Found"));
        user.setActive(false);
        userRepository.save(user);
    }
    public void activateUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User Not Found"));
        user.setActive(true);
        userRepository.save(user);
    }
    public List<UserResponseDTO> getInactiveUsers() {
        return userRepository.findAllByActiveFalse()
                .stream()
                .map(UserResponseDTO::new)
                .collect(Collectors.toList());
    }

}

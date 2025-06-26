package com.codingtrainers.duocoding.dto.output;

import com.codingtrainers.duocoding.entities.User;

public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}

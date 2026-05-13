package com.example.PasswordManager.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.PasswordManager.service.apiResponse.ApiResponseDTO;
import com.example.PasswordManager.user.dto.UserDTO;
import com.example.PasswordManager.user.modal.User;
import com.example.PasswordManager.user.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
        @Autowired
    private PasswordEncoder passwordEncoder;

    public ApiResponseDTO createUser(UserDTO dto) {

    Optional<User> existing = userRepository.findByEmail(dto.getEmail());

    if (existing.isPresent()) {
        return new ApiResponseDTO("Email already exists!", null);
    }

    User user = new User();
    user.setName(dto.getName());
    user.setEmail(dto.getEmail());
    user.setPassword(passwordEncoder.encode(dto.getPassword()));

    User savedUser = userRepository.save(user);

    return new ApiResponseDTO("User created successfully!", savedUser);
}
}

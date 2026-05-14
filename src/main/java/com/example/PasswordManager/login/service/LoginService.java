package com.example.PasswordManager.login.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.PasswordManager.login.dto.LoginDTO;
import com.example.PasswordManager.security.JwtUtil;
import com.example.PasswordManager.service.apiResponse.ApiResponseDTO;
import com.example.PasswordManager.user.modal.User;
import com.example.PasswordManager.user.repository.UserRepository;
@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public ApiResponseDTO login(LoginDTO dto) {

        User user = userRepository.findByEmail(dto.getEmail()).orElse(null);

        if (user == null) {
            return new ApiResponseDTO("User not found!", null);
        }

        boolean match = passwordEncoder.matches(dto.getPassword(), user.getPassword());

        if (!match) {
            return new ApiResponseDTO("Invalid Password!", null);
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new ApiResponseDTO("Login successful", token);
    }
}
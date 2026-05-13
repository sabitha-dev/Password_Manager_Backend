package com.example.PasswordManager.login.service;

import javax.security.auth.spi.LoginModule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PasswordManager.login.dto.LoginDTO;
import com.example.PasswordManager.user.modal.User;
import com.example.PasswordManager.user.repository.UserRepository;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;
   
    public String login(LoginDTO dto) {

User user = userRepository.findByEmail(dto.getEmail())
        .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return "Login successful";
    }
}

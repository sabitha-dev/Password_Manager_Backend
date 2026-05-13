package com.example.PasswordManager.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.PasswordManager.user.dto.UserDTO;
import com.example.PasswordManager.user.modal.User;
import com.example.PasswordManager.user.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
        @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(UserDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        String encodedPassword=passwordEncoder.encode(dto.getPassword());
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }
}

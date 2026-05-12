package com.example.PasswordManager.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PasswordManager.user.dto.UserDTO;
import com.example.PasswordManager.user.modal.User;
import com.example.PasswordManager.user.repository.UserRepository;

@Service
public class UserService {
        @Autowired
    private UserRepository userRepository;

    public User createUser(UserDTO dto){
User user=new User();
user.setName(dto.getName());
user.setEmail(dto.getEmail());
user.setPassword(dto.getPassword());

return userRepository.save(user);
    }
}

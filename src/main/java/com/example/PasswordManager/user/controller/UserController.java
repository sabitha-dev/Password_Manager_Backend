package com.example.PasswordManager.user.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.PasswordManager.service.apiResponse.ApiResponseDTO;
import com.example.PasswordManager.user.dto.UserDTO;
import com.example.PasswordManager.user.modal.User;
import com.example.PasswordManager.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/register")
public class UserController {
    @Autowired
    private UserService registerService;

    @PostMapping
    public ResponseEntity<ApiResponseDTO> createUser(@RequestBody UserDTO user) {
        User createUser = registerService.createUser(user);
        return ResponseEntity.ok(new ApiResponseDTO("User Created Successfully!", createUser));
    }

}

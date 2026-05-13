package com.example.PasswordManager.user.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.PasswordManager.service.apiResponse.ApiResponseDTO;
import com.example.PasswordManager.user.dto.UserDTO;
import com.example.PasswordManager.user.service.UserService;

import jakarta.validation.Valid;

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
    public ResponseEntity<ApiResponseDTO> createUser(@Valid @RequestBody UserDTO user) {
        System.out.println("innn"+user);
        ApiResponseDTO result = registerService.createUser(user);
        return ResponseEntity.ok( result);
    }

}

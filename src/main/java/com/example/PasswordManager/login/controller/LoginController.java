package com.example.PasswordManager.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PasswordManager.login.dto.LoginDTO;
import com.example.PasswordManager.login.service.LoginService;
import com.example.PasswordManager.service.apiResponse.ApiResponseDTO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<ApiResponseDTO> login(@RequestBody LoginDTO dto) {
       ApiResponseDTO result= loginService.login(dto);
        return ResponseEntity.ok(result);
    }
    

}

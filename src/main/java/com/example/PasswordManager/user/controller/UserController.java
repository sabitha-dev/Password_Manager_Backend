package com.example.PasswordManager.user.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.PasswordManager.user.dto.UserDTO;
import com.example.PasswordManager.user.modal.User;
import com.example.PasswordManager.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<User> createUser(@RequestBody UserDTO user){
return  new ResponseEntity<> (registerService.createUser(user), HttpStatus.CREATED);
    }
   
    
}

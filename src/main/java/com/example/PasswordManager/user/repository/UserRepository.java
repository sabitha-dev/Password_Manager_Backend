package com.example.PasswordManager.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PasswordManager.user.modal.User;

public interface UserRepository extends JpaRepository<User,Long> {
    
}

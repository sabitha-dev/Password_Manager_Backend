package com.example.PasswordManager.vault.model;

import java.time.LocalDateTime;

import com.example.PasswordManager.user.modal.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vault")
@Setter
@Getter
public class Vault {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "app_name", nullable = false)
    private String appName;

    @Column(name = "login_username", nullable = false)
    private String loginUsername;

    @Column(name = "encrypted_password", nullable = false, columnDefinition = "TEXT")
    private String encryptedPassword;

    @Column(name = "notes")
    private String notes;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

  @Column(name = "is_deleted")
private boolean isDeleted = false;
}

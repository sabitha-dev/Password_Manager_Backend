package com.example.PasswordManager.login.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginDTO {
    @NotBlank(message = "Email is required")
         @Email(message = "Email should be valid")

    String email;
    @NotBlank(message = "PAssword is required")
    String password;

   public LoginDTO(){}

   public LoginDTO(  String email,
    String password){
        this.email=email;
        this.password=password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
}

package com.example.PasswordManager.login.dto;

public class LoginDTO {
    String email;
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

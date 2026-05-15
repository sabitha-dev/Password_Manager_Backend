package com.example.PasswordManager.vault.dto;

public class VaultDTO {
    private Long id;
  private Long userId; 
    private String appName;
    private String loginUsername;
    private String password; 
    private String notes;

    public VaultDTO() {}
    public VaultDTO(Long userId,String appName,
     String loginUsername,
     String password,
     String notes) {
        this.userId=userId;
        this.appName=appName;
        this.loginUsername=loginUsername;
        this.password=password;
        this.notes=notes;
     }

 public Long getUserId() {
        return userId;
    }
public void setId(Long id) {
    this.id = id;
}
public Long getId() {
    return id;
}
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getLoginUsername() {
        return loginUsername;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

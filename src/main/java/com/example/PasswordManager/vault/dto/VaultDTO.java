package com.example.PasswordManager.vault.dto;

public class VaultDTO {

    private String appName;
    private String loginUsername;
    private String password; 
    private String notes;
    private Boolean isDeleted;

    public VaultDTO() {}
    public VaultDTO(String appName,
     String loginUsername,
     String password,
     String notes,Boolean isDeleted) {
        this.appName=appName;
        this.loginUsername=loginUsername;
        this.password=password;
        this.notes=notes;
        this.isDeleted=isDeleted;
     }


    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
public void setIsDeleted(Boolean isDeleted) {
    this.isDeleted = isDeleted;
}
public Boolean getIsDeleted() {
    return isDeleted;
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
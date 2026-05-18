package com.example.PasswordManager.vault.dto;

public class VaultResponseDTO {

    private Long id;
    private String appName;
    private String loginUsername;
    private String encryptedPassword;
    private String notes;
    private String siteUrl;

    public VaultResponseDTO() {
    }

    public Long getId() {
        return id;
    }
public void setSiteUrl(String siteUrl) {
    this.siteUrl = siteUrl;
}
public String getSiteUrl() {
    return siteUrl;
}
    public void setId(Long id) {
        this.id = id;
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

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
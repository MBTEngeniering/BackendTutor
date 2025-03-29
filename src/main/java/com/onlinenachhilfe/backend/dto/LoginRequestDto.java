package com.onlinenachhilfe.backend.dto;

public class LoginRequestDto {

    private String emailOrPhoneNumber;
    private String password;

    // Getter und Setter
    public String getEmailOrPhoneNumber() {
        return emailOrPhoneNumber;
    }

    public void setEmailOrPhoneNumber(String emailOrPhoneNumber) {
        this.emailOrPhoneNumber = emailOrPhoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

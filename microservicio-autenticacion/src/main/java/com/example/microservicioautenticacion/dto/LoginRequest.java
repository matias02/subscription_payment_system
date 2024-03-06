package com.example.microservicioautenticacion.dto;

public class LoginRequest {
    private String usernameOrEmail;
    private String password;
    private String username;


    public LoginRequest() {
    }

    public LoginRequest(String usernameOrEmail, String password) {
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
        this.username = username;

    }

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.example.liberphile;

public class LoginResult {
    private String name;
    private String email;

    public LoginResult(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

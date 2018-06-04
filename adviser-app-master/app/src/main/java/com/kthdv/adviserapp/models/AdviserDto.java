package com.kthdv.adviserapp.models;

public class AdviserDto {
    private String fullName;
    private String username;
    private String password;
    private String role;

    public AdviserDto(String fullName, String username, String password) {
        this.fullName = fullName.trim();
        this.username = username.trim();
        this.password = password;
        this.role = "adviser";
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

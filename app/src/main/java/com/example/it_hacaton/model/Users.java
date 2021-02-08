package com.example.it_hacaton.model;

public class Users {
    private String name;
    private String middle_name;
    private String last_name;
    private String email;
    private String password;
    private String status;

    public Users(String name, String middle_name, String last_name, String email, String password, String status) {
        this.name = name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public Users() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

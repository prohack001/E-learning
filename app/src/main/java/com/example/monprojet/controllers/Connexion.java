package com.example.monprojet.controllers;

public class Connexion {
    private String email;
    private String password;

    public Connexion(String email, String password) {
        this.email = email;
        this.password = password;
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

    @Override
    public String toString() {
        return "Connexion{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

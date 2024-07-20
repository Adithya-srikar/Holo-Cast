package com.example.arcoreagora.models;

public class User {
    public String name;
    public String email;
    public String username;
    public String password;
    public String role;

    // Default constructor required for calls to DataSnapshot.getValue(User.class)
    public User() {
    }

    public User(String name, String email, String username, String password , String role) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role= role;
    }

    // Getters and setters can be added here if needed
}

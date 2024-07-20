package com.example.arcoreagora.models;

public class Recent {
    public String username;
    public String channel;
    public String joinedDateTime; // Added field for date and time

    // Default constructor (required for Firebase)
    public Recent() {
    }

    public Recent(String username, String channel, String joinedDateTime) {
        this.username = username;
        this.channel = channel;
        this.joinedDateTime = joinedDateTime;
    }
}
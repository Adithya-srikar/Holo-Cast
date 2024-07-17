package com.example.arcoreagora.models;

public class Recent {
    public String username;
    public String channel;

    // Default constructor required for calls to DataSnapshot.getValue(User.class)
    public Recent() {
    }

    public Recent( String username, String channel) {
        this.username = username;
        this.channel = channel;
    }
}

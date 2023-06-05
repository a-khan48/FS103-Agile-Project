package org.RMS.models;
public class User {
    private String username;
    private String hashedPassword;
    private Role role;

    public enum Role {
        STAFF, MANAGER
    }

    // Constructors

    // Getters and Setters
}
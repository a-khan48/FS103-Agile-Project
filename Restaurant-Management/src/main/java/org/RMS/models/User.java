package org.RMS.models;
public class User {
    private String username;
    private String hashedPassword;
    private Role role;

    public enum Role {
        STAFF, MANAGER
    }

    // Constructors

    public User(String username, String hashedPassword, Role role) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.role = role;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole (Role role) {
        this.role = role;
    }

}
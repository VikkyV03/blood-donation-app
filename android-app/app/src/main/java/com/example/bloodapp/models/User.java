// android-app/app/src/main/java/com/example/bloodapp/models/User.java

package com.example.bloodapp.models;

public class User {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String role;
    private String token; // received from backend on login

    // Constructors
    public User() {}

    // Constructor for login
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Constructor for registration
    public User(String name, String email, String password, String phone, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}

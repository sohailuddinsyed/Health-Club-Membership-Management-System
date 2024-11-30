// models/User.java
package models;

public class User {
    private final String username;
    private final String email;
    private final String password;
    private final String role;

    public User(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    // Login logic
    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }
}

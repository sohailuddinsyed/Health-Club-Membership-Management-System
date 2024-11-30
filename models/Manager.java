// models/Manager.java
package models;

public class Manager extends User {
    private String additionalInfo; // Optional field for the fifth argument

    // Constructor with four arguments
    public Manager(String username, String email, String password, String role) {
        super(username, email, password, role);
    }

    // Constructor with five arguments (if needed)
    public Manager(String username, String email, String password, String role, String additionalInfo) {
        super(username, email, password, role);
        this.additionalInfo = additionalInfo;
    }

    // Getters and setters for additionalInfo (optional)
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}

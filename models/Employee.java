// models/Employee.java
package models;

public class Employee extends User {
    private String additionalInfo; // Optional field if the fifth argument is needed

    // Constructor with four arguments
    public Employee(String username, String email, String password, String role) {
        super(username, email, password, role);
    }

    // Constructor with five arguments (if needed)
    public Employee(String username, String email, String password, String role, String additionalInfo) {
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

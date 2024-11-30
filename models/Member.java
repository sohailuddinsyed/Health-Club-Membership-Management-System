package models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Member {
    private String memberId;
    private String name;
    private String email;
    private String membershipType;
    private String startDate;
    private String endDate;
    private String password; // Password for login
    private ArrayList<LocalDate> visitLog; // To track visits

    // Constructor
    public Member(String memberId, String name, String email, String membershipType, String startDate, String endDate, String password) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.membershipType = membershipType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.password = password;
        this.visitLog = new ArrayList<>();
    }

    // Getters and setters
    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Login method
    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }

    // Record a visit
    public void recordVisit() {
        visitLog.add(LocalDate.now());
    }

    // Get total number of visits
    public int getVisitCount() {
        return visitLog.size();
    }

    // Get visit log (for debugging or reporting)
    public ArrayList<LocalDate> getVisitLog() {
        return visitLog;
    }
}

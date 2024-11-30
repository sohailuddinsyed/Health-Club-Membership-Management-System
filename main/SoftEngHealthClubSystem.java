// main/SoftEngHealthClubSystem.java
package main;

import java.util.ArrayList;
import models.Employee;
import models.Manager;
import models.Member;

public class SoftEngHealthClubSystem {
    private static final ArrayList<Member> members = new ArrayList<>();
    static final ArrayList<Employee> employees = new ArrayList<>();
    static final ArrayList<Manager> managers = new ArrayList<>();

    // Initialize some dummy data
    static {
        // Dummy members
        members.add(new Member("1001", "John Doe", "john.doe@example.com", "Yearly", "2024-01-01", "2024-12-31", "123"));
        members.add(new Member("1002", "Jane Smith", "jane.smith@example.com", "Monthly", "2024-10-01", "2024-10-31", "456"));

        // Dummy employees
        employees.add(new Employee("e001", "Alice", "alice@softeng.com", "password123", "Employee"));

        // Dummy manager
        managers.add(new Manager("m001", "Bob", "bob@softeng.com", "manager123", "Manager"));
    }

    // Add member
    public static void addMember(Member member) {
        members.add(member);
        System.out.println("Member added successfully: " + member.getName());
    }

    // Remove member
    public static boolean removeMember(String memberId) {
        return members.removeIf(member -> member.getMemberId().equals(memberId));
    }

    // Add employee
    public static void addEmployee(Employee employee) {
        employees.add(employee);
        System.out.println("Employee added successfully: " + employee.getUsername());
    }

    // Remove employee
    public static boolean removeEmployee(String employeeId) {
        return employees.removeIf(employee -> employee.getUsername().equals(employeeId));
    }

    // List all members
    public static void listMembers() {
        System.out.println("Members:");
        for (Member member : members) {
            System.out.println("- " + member.getMemberId() + ": " + member.getName());
        }
    }

    // List all employees
    public static void listEmployees() {
        System.out.println("Employees:");
        for (Employee employee : employees) {
            System.out.println("- " + employee.getUsername() + ": " + employee.getEmail());
        }
    }
    public static ArrayList<Member> getMembers() {
        return members;
    }

    public static ArrayList<Employee> getEmployees() {
        return employees;
    }

    public static ArrayList<Manager> getManagers() {
        return managers;
    }

}

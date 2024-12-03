package main;

import java.util.ArrayList;
import models.Employee;
import models.Manager;
import models.Member;

public class SoftEngHealthClubSystem {
    private static final ArrayList<Member> members = new ArrayList<>();
    private static final ArrayList<Employee> employees = new ArrayList<>();
    private static final ArrayList<Manager> managers = new ArrayList<>();

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

    // Add a new member
    // Add a new member with password
    public static void addMember(Member member) {
        if (getMemberById(member.getMemberId()) != null) {
            System.out.println("Member with ID " + member.getMemberId() + " already exists.");
            return;
        }
        if (member.getPassword() == null || member.getPassword().isEmpty()) {
            System.out.println("Password cannot be empty.");
            return;
        }
        members.add(member);
        System.out.println("Member added successfully: " + member.getName());
    }


    // Remove a member by ID
    public static boolean removeMember(String memberId) {
        return members.removeIf(member -> member.getMemberId().equals(memberId));
    }

    // Get a member by ID
    public static Member getMemberById(String memberId) {
        return members.stream().filter(member -> member.getMemberId().equals(memberId)).findFirst().orElse(null);
    }

    // Add a new employee
    public static void addEmployee(Employee employee) {
        if (getEmployeeByUsername(employee.getUsername()) != null) {
            System.out.println("Employee with username " + employee.getUsername() + " already exists.");
            return;
        }
        employees.add(employee);
        System.out.println("Employee added successfully: " + employee.getUsername());
    }

    // Remove an employee by username
    public static boolean removeEmployee(String employeeId) {
        return employees.removeIf(employee -> employee.getUsername().equals(employeeId));
    }

    // Get an employee by username
    public static Employee getEmployeeByUsername(String username) {
        return employees.stream().filter(employee -> employee.getUsername().equals(username)).findFirst().orElse(null);
    }

    // Add a new manager
    public static void addManager(Manager manager) {
        if (getManagerByUsername(manager.getUsername()) != null) {
            System.out.println("Manager with username " + manager.getUsername() + " already exists.");
            return;
        }
        managers.add(manager);
        System.out.println("Manager added successfully: " + manager.getUsername());
    }

    // Get a manager by username
    public static Manager getManagerByUsername(String username) {
        return managers.stream().filter(manager -> manager.getUsername().equals(username)).findFirst().orElse(null);
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

    // List all managers
    public static void listManagers() {
        System.out.println("Managers:");
        for (Manager manager : managers) {
            System.out.println("- " + manager.getUsername() + ": " + manager.getEmail());
        }
    }

    // Getters for collections
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

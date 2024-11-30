package main;

import services.MembershipValidationService;
import services.MemberService;

import models.Member;
import models.Employee;
import models.Manager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nWelcome to the SoftEng Health Club!");
            System.out.println("1. Swipe In");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Membership ID: ");
                    String memberId = scanner.nextLine();
                    MembershipValidationService.validateMembership(memberId);
                }
                case 2 -> handleLogin(scanner);
                case 3 -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleLogin(Scanner scanner) {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
    
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
    
        // Authenticate manager
        for (Manager manager : SoftEngHealthClubSystem.getManagers()) {
            if (manager.login(email, password)) {
                System.out.println("Welcome, Manager " + manager.getUsername() + "!");
                managerMenu(scanner);
                return;
            }
        }
    
        // Authenticate employee
        for (Employee employee : SoftEngHealthClubSystem.getEmployees()) {
            if (employee.login(email, password)) {
                System.out.println("Welcome, Employee " + employee.getUsername() + "!");
                employeeMenu(scanner);
                return;
            }
        }
    
        // Authenticate member
        for (Member member : SoftEngHealthClubSystem.getMembers()) {
            if (member.login(email, password)) {
                System.out.println("Welcome, Member " + member.getName() + "!");
                
                // Record the visit
                member.recordVisit();
                System.out.println("Your visit has been logged. Total Visits: " + member.getVisitCount());
                
                memberMenu(member, scanner);
                return;
            }
        }
    
        System.out.println("Invalid credentials. Please try again.");
    }
    
    private static void employeeMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\nEmployee Menu");
            System.out.println("1. Add Member");
            System.out.println("2. Remove Member");
            System.out.println("3. List Members");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> MemberService.addNewMember();
                case 2 -> {
                    System.out.print("Enter Member ID to remove: ");
                    String memberId = scanner.nextLine();
                    if (SoftEngHealthClubSystem.removeMember(memberId)) {
                        System.out.println("Member removed successfully.");
                    } else {
                        System.out.println("Member not found.");
                    }
                }
                case 3 -> SoftEngHealthClubSystem.listMembers();
                case 4 -> {
                    System.out.println("Logged out.");
                    running = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void managerMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\nManager Menu");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. List Employees");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Employee username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter Employee email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Employee password: ");
                    String password = scanner.nextLine();
                    SoftEngHealthClubSystem.addEmployee(new Employee(username, email, password, "Employee"));
                }
                case 2 -> {
                    System.out.print("Enter Employee username to remove: ");
                    String empId = scanner.nextLine();
                    if (SoftEngHealthClubSystem.removeEmployee(empId)) {
                        System.out.println("Employee removed successfully.");
                    } else {
                        System.out.println("Employee not found.");
                    }
                }
                case 3 -> SoftEngHealthClubSystem.listEmployees();
                case 4 -> {
                    System.out.println("Logged out.");
                    running = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void memberMenu(Member member, Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\nMember Menu");
            System.out.println("1. View Visit Count");
            System.out.println("2. View Membership End Date");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> System.out.println("Total Visits: " + member.getVisitCount());
                case 2 -> System.out.println("Membership End Date: " + member.getEndDate());
                case 3 -> {
                    System.out.println("Logged out.");
                    running = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import models.Member;
import models.Employee;
import models.Manager;
import services.MembershipValidationService;

import java.awt.*;
import java.awt.event.ActionEvent;


public class HealthClubUI {
    public static void main(String[] args) {
        // Create the main JFrame
        JFrame mainFrame = new JFrame("Health Club Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 300);
        mainFrame.setLayout(new GridLayout(3, 1));

        // Create buttons
        JButton swipeInButton = new JButton("Swipe In");
        JButton loginButton = new JButton("Login");
        JButton exitButton = new JButton("Exit");

        // Add buttons to the frame
        mainFrame.add(swipeInButton);
        mainFrame.add(loginButton);
        mainFrame.add(exitButton);

        // Add action listeners
        swipeInButton.addActionListener((ActionEvent e) -> {
            showSwipeInDialog(mainFrame);
        });

        loginButton.addActionListener((ActionEvent e) -> {
            showLoginDialog(mainFrame);
        });

        exitButton.addActionListener(e -> System.exit(0));

        // Make the frame visible
        mainFrame.setVisible(true);
    }

    // Swipe In Dialog
    private static void showSwipeInDialog(JFrame parent) {
        String memberId = JOptionPane.showInputDialog(parent, "Enter Membership ID:");
        if (memberId != null) {
            boolean isValid = MembershipValidationService.validateMembership(memberId);
            if (isValid) {
                JOptionPane.showMessageDialog(parent, "Access Granted. Enjoy your workout!");
            } else {
                JOptionPane.showMessageDialog(parent, "Access Denied. Please contact the front desk.");
            }
        }
    }

    // Login Dialog
    private static void showLoginDialog(JFrame parent) {
        // Create a login panel
        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        loginPanel.add(new JLabel("Email:"));
        loginPanel.add(emailField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);

        int result = JOptionPane.showConfirmDialog(parent, loginPanel, "Login", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            handleLogin(email, password, parent);
        }
    }

    // Handle Login
    private static void handleLogin(String email, String password, JFrame parent) {
        // Authenticate Manager
        for (Manager manager : SoftEngHealthClubSystem.getManagers()) {
            if (manager.login(email, password)) {
                JOptionPane.showMessageDialog(parent, "Welcome, Manager " + manager.getUsername() + "!");
                showManagerDashboard(parent);
                return;
            }
        }

        // Authenticate Employee
        for (Employee employee : SoftEngHealthClubSystem.getEmployees()) {
            if (employee.login(email, password)) {
                JOptionPane.showMessageDialog(parent, "Welcome, Employee " + employee.getUsername() + "!");
                showEmployeeDashboard(parent);
                return;
            }
        }

        // Authenticate Member
        for (Member member : SoftEngHealthClubSystem.getMembers()) {
            if (member.login(email, password)) {
                JOptionPane.showMessageDialog(parent, "Welcome, Member " + member.getName() + "!");
                showMemberDashboard(member, parent);
                return;
            }
        }

        JOptionPane.showMessageDialog(parent, "Invalid credentials. Please try again.");
    }
    private static void showMemberDashboard(Member member, JFrame parent) {
        JFrame memberFrame = new JFrame("Member Dashboard");
        memberFrame.setSize(400, 200);
        memberFrame.setLayout(new GridLayout(2, 1));

        JButton visitCountButton = new JButton("View Visit Count");
        JButton membershipButton = new JButton("View Membership End Date");

        memberFrame.add(visitCountButton);
        memberFrame.add(membershipButton);

        visitCountButton.addActionListener(e -> JOptionPane.showMessageDialog(memberFrame,
                "Total Visits: " + member.getVisitCount()));
        membershipButton.addActionListener(e -> JOptionPane.showMessageDialog(memberFrame,
                "Membership End Date: " + member.getEndDate()));

        memberFrame.setVisible(true);
    }

    private static void showEmployeeDashboard(JFrame parent) {
        JFrame employeeFrame = new JFrame("Employee Dashboard");
        employeeFrame.setSize(400, 200);
        employeeFrame.setLayout(new GridLayout(4, 1));
    
        JButton addMemberButton = new JButton("Add Member");
        JButton removeMemberButton = new JButton("Remove Member");
        JButton listMembersButton = new JButton("List Members");
        JButton logoutButton = new JButton("Logout");
    
        employeeFrame.add(addMemberButton);
        employeeFrame.add(removeMemberButton);
        employeeFrame.add(listMembersButton);
        employeeFrame.add(logoutButton);
    
        // Add Member Button with UI
        addMemberButton.addActionListener(e -> showAddMemberDialog(employeeFrame));
    
        // Remove Member Button
        removeMemberButton.addActionListener(e -> {
            String memberId = JOptionPane.showInputDialog(employeeFrame, "Enter Member ID to Remove:");
            if (memberId != null) {
                if (SoftEngHealthClubSystem.removeMember(memberId)) {
                    JOptionPane.showMessageDialog(employeeFrame, "Member removed successfully.");
                } else {
                    JOptionPane.showMessageDialog(employeeFrame, "Member not found.");
                }
            }
        });
    
        // List Members Button
        listMembersButton.addActionListener(e -> showMemberTable(employeeFrame));
    
        // Logout Button
        logoutButton.addActionListener(e -> employeeFrame.dispose());
    
        employeeFrame.setVisible(true);
    }

    private static void showAddMemberDialog(JFrame parent) {
        // Create a panel for the form
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
    
        // Input fields
        JTextField memberIdField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField membershipTypeField = new JTextField();
        JTextField startDateField = new JTextField();
        JTextField endDateField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
    
        // Add fields to the form panel
        formPanel.add(new JLabel("Member ID:"));
        formPanel.add(memberIdField);
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Membership Type:"));
        formPanel.add(membershipTypeField);
        formPanel.add(new JLabel("Start Date (YYYY-MM-DD):"));
        formPanel.add(startDateField);
        formPanel.add(new JLabel("End Date (YYYY-MM-DD):"));
        formPanel.add(endDateField);
        formPanel.add(new JLabel("Password:"));
        formPanel.add(passwordField);
    
        // Show the dialog
        int result = JOptionPane.showConfirmDialog(parent, formPanel, "Add New Member",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    
        if (result == JOptionPane.OK_OPTION) {
            // Collect input data
            String memberId = memberIdField.getText();
            String name = nameField.getText();
            String email = emailField.getText();
            String membershipType = membershipTypeField.getText();
            String startDate = startDateField.getText();
            String endDate = endDateField.getText();
            String password = new String(passwordField.getPassword());
    
            // Validate inputs
            if (memberId.isEmpty() || name.isEmpty() || email.isEmpty() || membershipType.isEmpty()
                    || startDate.isEmpty() || endDate.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(parent, "All fields are required, including password.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // Add the new member to the system
            Member newMember = new Member(memberId, name, email, membershipType, startDate, endDate, password);
            SoftEngHealthClubSystem.addMember(newMember);
    
            JOptionPane.showMessageDialog(parent, "Member added successfully!");
        }
    }
    
    

    private static void showMemberTable(JFrame parent) {
        // Define column names
        String[] columnNames = {"Member ID", "Name", "Email", "Membership Type", "End Date"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Fetch and populate member data
        for (Member member : SoftEngHealthClubSystem.getMembers()) {
            String[] row = {
                member.getMemberId(),
                member.getName(),
                member.getEmail(),
                member.getMembershipType(),
                member.getEndDate()
            };
            tableModel.addRow(row);
        }

        // Create the JTable with the table model
        JTable memberTable = new JTable(tableModel);

        // Create a scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(memberTable);

        // Display the table in a modal dialog
        JDialog tableDialog = new JDialog(parent, "Member List", true);
        tableDialog.setSize(600, 400);
        tableDialog.setLayout(new BorderLayout());
        tableDialog.add(scrollPane, BorderLayout.CENTER);

        // Add a close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> tableDialog.dispose());
        tableDialog.add(closeButton, BorderLayout.SOUTH);

        tableDialog.setVisible(true);
    }

    private static void showManagerDashboard(JFrame parent) {
        JFrame managerFrame = new JFrame("Manager Dashboard");
        managerFrame.setSize(400, 200);
        managerFrame.setLayout(new GridLayout(4, 1));
    
        JButton addEmployeeButton = new JButton("Add Employee");
        JButton removeEmployeeButton = new JButton("Remove Employee");
        JButton listEmployeesButton = new JButton("List Employees");
        JButton logoutButton = new JButton("Logout");
    
        managerFrame.add(addEmployeeButton);
        managerFrame.add(removeEmployeeButton);
        managerFrame.add(listEmployeesButton);
        managerFrame.add(logoutButton);
    
        // Add Employee Button
        addEmployeeButton.addActionListener(e -> {
            String username = JOptionPane.showInputDialog(managerFrame, "Enter Employee Username:");
            String email = JOptionPane.showInputDialog(managerFrame, "Enter Employee Email:");
            String password = JOptionPane.showInputDialog(managerFrame, "Enter Employee Password:");
            if (username != null && email != null && password != null) {
                SoftEngHealthClubSystem.addEmployee(new Employee(username, email, password, "Employee"));
                JOptionPane.showMessageDialog(managerFrame, "Employee added successfully.");
            }
        });
    
        // Remove Employee Button
        removeEmployeeButton.addActionListener(e -> {
            String employeeId = JOptionPane.showInputDialog(managerFrame, "Enter Employee Username to Remove:");
            if (employeeId != null) {
                if (SoftEngHealthClubSystem.removeEmployee(employeeId)) {
                    JOptionPane.showMessageDialog(managerFrame, "Employee removed successfully.");
                } else {
                    JOptionPane.showMessageDialog(managerFrame, "Employee not found.");
                }
            }
        });
    
        // List Employees Button
        listEmployeesButton.addActionListener(e -> showEmployeeTable(managerFrame));
    
        // Logout Button
        logoutButton.addActionListener(e -> managerFrame.dispose());
    
        managerFrame.setVisible(true);
    }

    private static void showEmployeeTable(JFrame parent) {
        // Define column names
        String[] columnNames = {"Username", "Email", "Role"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
    
        // Fetch and populate employee data
        for (Employee employee : SoftEngHealthClubSystem.getEmployees()) {
            String[] row = {
                employee.getUsername(),
                employee.getEmail(),
                employee.getRole()
            };
            tableModel.addRow(row);
        }
    
        // Create the JTable with the table model
        JTable employeeTable = new JTable(tableModel);
    
        // Create a scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(employeeTable);
    
        // Display the table in a modal dialog
        JDialog tableDialog = new JDialog(parent, "Employee List", true);
        tableDialog.setSize(600, 400);
        tableDialog.setLayout(new BorderLayout());
        tableDialog.add(scrollPane, BorderLayout.CENTER);
    
        // Add a close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> tableDialog.dispose());
        tableDialog.add(closeButton, BorderLayout.SOUTH);
    
        tableDialog.setVisible(true);
    }
    
}

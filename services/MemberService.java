// services/MemberService.java
package services;

import database.MemberDatabase;
import java.util.Scanner;
import models.Member;

public class MemberService {
    public static void addNewMember() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Membership Type (e.g., Monthly, Yearly): ");
        String membershipType = scanner.nextLine();

        System.out.print("Enter Start Date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();

        System.out.print("Enter End Date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        // Create a new member object
        Member newMember = new Member(memberId, name, email, membershipType, startDate, endDate, password);

        // Add the member to the database
        MemberDatabase.addMember(newMember);

        System.out.println("New member successfully added!");
    }
}

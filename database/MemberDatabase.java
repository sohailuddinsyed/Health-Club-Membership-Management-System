// database/MemberDatabase.java
package database;

import java.util.HashMap;
import models.Member;

public class MemberDatabase {
    private static final HashMap<String, Member> members = new HashMap<>();

    public static void addMember(Member member) {
        members.put(member.getMemberId(), member);
        System.out.println("Member added successfully: " + member.getName());
    }

    public static Member getMember(String memberId) {
        return members.get(memberId);
    }

    public static void listAllMembers() {
        System.out.println("List of Members:");
        for (Member member : members.values()) {
            System.out.println("- " + member.getMemberId() + ": " + member.getName());
        }
    }
    static {
        // Adding some dummy members
        members.put("1001", new Member("1001", "John Doe", "john.doe@example.com", "Yearly", "2024-01-01", "2024-12-31", "123"));
        members.put("1002", new Member("1002", "Jane Smith", "jane.smith@example.com", "Monthly", "2024-10-01", "2024-10-31", "456"));
    }
}
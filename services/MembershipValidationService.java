// services/MembershipValidationService.java
package services;

import database.MemberDatabase;
import java.time.LocalDate;
import models.Member;

public class MembershipValidationService {
    public static boolean validateMembership(String memberId) {
        Member member = MemberDatabase.getMember(memberId);
        if (member == null) {
            System.out.println("Membership ID not found.");
            return false;
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate membershipEndDate = LocalDate.parse(member.getEndDate());
        if (membershipEndDate.isBefore(currentDate)) {
            System.out.println("Membership has expired. Please renew.");
            return false;
        }

        member.recordVisit();
        System.out.println("Membership is valid. Visit recorded.");
        System.out.println("Total Visits: " + member.getVisitCount());
        return true;
    }
}

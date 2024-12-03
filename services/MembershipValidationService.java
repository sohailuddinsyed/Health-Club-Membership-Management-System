package services;

import database.MemberDatabase;
import models.Member;
import main.SoftEngHealthClubSystem;

import java.time.LocalDate;

public class MembershipValidationService {
    public static boolean validateMembership(String memberId) {
        Member member = SoftEngHealthClubSystem.getMembers().stream()
                .filter(m -> m.getMemberId().equals(memberId))
                .findFirst()
                .orElse(null);

        if (member == null) {
            System.out.println("Membership ID not found.");
            return false;
        }

        // Check if membership is expired
        LocalDate currentDate = LocalDate.now();
        LocalDate membershipEndDate = LocalDate.parse(member.getEndDate());
        if (membershipEndDate.isBefore(currentDate)) {
            System.out.println("Membership has expired. Please renew.");
            return false;
        }

        // Record the visit
        member.recordVisit();
        System.out.println("Membership is valid. Visit recorded.");
        return true;
    }
}

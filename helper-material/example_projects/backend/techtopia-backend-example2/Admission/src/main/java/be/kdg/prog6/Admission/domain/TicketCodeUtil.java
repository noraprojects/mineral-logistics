package be.kdg.prog6.Admission.domain;

import java.util.UUID;

public class TicketCodeUtil {
    public static boolean isValidUUID(String uuidString) {
        if (uuidString == null || uuidString.length() != 36) {
            return false;
        }

        try {
            UUID uuid = UUID.fromString(uuidString);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}

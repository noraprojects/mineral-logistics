package be.kdg.prog6.family.events;

import java.util.UUID;

public record FamilyReceivedPiggyBankEvent(UUID owner, String name, UUID piggyBank) {
}

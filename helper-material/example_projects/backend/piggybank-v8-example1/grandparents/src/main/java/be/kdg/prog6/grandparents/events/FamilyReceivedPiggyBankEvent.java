package be.kdg.prog6.grandparents.events;

import java.util.UUID;

public record FamilyReceivedPiggyBankEvent(UUID owner, String name, UUID piggyBank) implements Event {
}

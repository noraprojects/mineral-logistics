package be.kdg.prog6.parkManagement.events;

import java.util.UUID;

public record ParkManagementReceivedRefreshmentStandEvent(UUID standUUID) implements Event {
}

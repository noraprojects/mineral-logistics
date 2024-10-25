package be.kdg.prog6.Park.events;

import java.util.UUID;

public record ParkReceivedStandEvent(UUID standUUID) implements Event{
}

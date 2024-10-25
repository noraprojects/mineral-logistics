package be.kdg.prog6.Park.events;

import java.util.UUID;

public record ParkDeleteStandEvent(UUID standUUID) implements Event{
}

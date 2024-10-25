package be.kdg.prog6.Park.events;

import java.util.UUID;

public record ParkReceivedThroughputEvent(UUID attractionID) implements Event {
}

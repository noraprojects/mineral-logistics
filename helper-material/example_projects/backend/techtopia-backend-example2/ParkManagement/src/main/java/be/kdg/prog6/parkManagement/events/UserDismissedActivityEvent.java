package be.kdg.prog6.parkManagement.events;

import java.util.UUID;

public record UserDismissedActivityEvent(UUID codeP) implements Event {
}

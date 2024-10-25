package be.kdg.prog6.parkManagement.events;

import java.util.UUID;

public record UserAdmittedActivityEvent(UUID codeP) implements Event {
}

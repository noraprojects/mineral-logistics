package be.kdg.prog6.common.events.facades;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateAppointmentCommand(UUID truckUUID,
                                       UUID sellerUUID,
                                       String materialType,
                                       LocalDateTime start,
                                       LocalDateTime end) {
}

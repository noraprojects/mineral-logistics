package be.kdg.prog6.common.facades;

import java.time.LocalDate;
import java.util.UUID;

public record CreateAppointmentCommand(UUID personUUID, LocalDate date) {
}

package be.kdg.prog6.family.events;

import java.time.LocalDate;
import java.util.UUID;

public record AgendaActivityCreated(UUID personUUID, LocalDate date) {
}

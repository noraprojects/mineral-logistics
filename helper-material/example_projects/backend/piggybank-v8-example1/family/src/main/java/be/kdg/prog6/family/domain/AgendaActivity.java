package be.kdg.prog6.family.domain;

import java.time.LocalDate;
import java.util.UUID;

public record AgendaActivity(UUID personUUID, LocalDate date, AgendaAction agendaAction, String someDescription) {
}

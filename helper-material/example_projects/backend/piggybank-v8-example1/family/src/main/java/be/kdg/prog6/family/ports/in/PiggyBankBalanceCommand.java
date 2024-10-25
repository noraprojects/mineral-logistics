package be.kdg.prog6.family.ports.in;

import java.time.LocalDateTime;
import java.util.UUID;

public record PiggyBankBalanceCommand(UUID owner, LocalDateTime start, LocalDateTime end) {
}

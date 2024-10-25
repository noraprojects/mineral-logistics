package be.kdg.prog6.ticketing.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TicketingActivity(TicketingAction action, BigDecimal amount, LocalDateTime pit) {
}

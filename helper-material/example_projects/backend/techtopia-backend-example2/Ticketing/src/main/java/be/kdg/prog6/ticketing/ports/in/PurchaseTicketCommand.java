package be.kdg.prog6.ticketing.ports.in;

import be.kdg.prog6.ticketing.domain.Guest;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PurchaseTicketCommand(BigDecimal price, String guest, LocalDate startDate, LocalDate endDate) {
}

package be.kdg.prog6.ticketing.events;

import be.kdg.prog6.ticketing.domain.Guest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


public record TicketingReceivedTicketEvent(Guest.GuestUUID guestUUID, LocalDateTime pit, BigDecimal amount) {
}

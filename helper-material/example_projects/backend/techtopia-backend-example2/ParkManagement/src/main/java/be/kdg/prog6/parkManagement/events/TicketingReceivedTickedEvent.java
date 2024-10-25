package be.kdg.prog6.parkManagement.events;

import be.kdg.prog6.parkManagement.domain.Guest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


public record TicketingReceivedTickedEvent(Guest.GuestUUID guestUUID, LocalDateTime pit, BigDecimal amount) implements Event{};


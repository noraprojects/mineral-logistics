package be.kdg.prog6.ticketing.ports.out;

import be.kdg.prog6.ticketing.domain.Guest;
import be.kdg.prog6.ticketing.domain.Ticket;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface TicketingActivityLoadPort {
    Optional<Ticket> loadTicket(Guest.GuestUUID guestUUID, LocalDateTime start, LocalDateTime end);
}

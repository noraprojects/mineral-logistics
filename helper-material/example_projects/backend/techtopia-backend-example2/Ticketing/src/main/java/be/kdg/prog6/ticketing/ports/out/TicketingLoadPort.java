package be.kdg.prog6.ticketing.ports.out;

import be.kdg.prog6.ticketing.adapters.out.db.TicketingJpaEntity;
import be.kdg.prog6.ticketing.domain.Guest;
import be.kdg.prog6.ticketing.domain.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketingLoadPort {
    List<TicketingJpaEntity> loadTicketsByGuest(Guest.GuestUUID username);
}

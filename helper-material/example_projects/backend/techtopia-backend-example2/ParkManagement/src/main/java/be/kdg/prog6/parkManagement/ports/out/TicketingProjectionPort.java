package be.kdg.prog6.parkManagement.ports.out;

import be.kdg.prog6.parkManagement.domain.Ticket;

import java.util.Optional;
import java.util.UUID;

public interface TicketingProjectionPort {
    Optional<Ticket> loadTicket(UUID ticketUUID);

    void saveTicket(Ticket ticket);
}

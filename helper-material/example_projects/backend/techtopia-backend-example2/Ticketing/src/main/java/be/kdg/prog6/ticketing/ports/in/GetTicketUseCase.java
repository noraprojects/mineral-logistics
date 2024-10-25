package be.kdg.prog6.ticketing.ports.in;

import be.kdg.prog6.ticketing.adapters.out.db.TicketingJpaEntity;
import be.kdg.prog6.ticketing.domain.Ticket;

import java.util.List;
import java.util.Optional;

public interface GetTicketUseCase {
    List<TicketingJpaEntity> getTicket(GetTicketCommand getTicketCommand);
}

package be.kdg.prog6.parkManagement.adapters.out.db;

import be.kdg.prog6.parkManagement.domain.Ticket;
import be.kdg.prog6.parkManagement.ports.out.TicketingProjectionPort;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TicketingDBAdapter implements TicketingProjectionPort {

    private final TicketingRepository ticketingRepository;


    public TicketingDBAdapter(TicketingRepository ticketingRepository) {
        this.ticketingRepository = ticketingRepository;
    }


    @Override
    public Optional<Ticket> loadTicket(UUID ticketUUID) {

        return Optional.empty();
    }

    @Override
    public void saveTicket(Ticket ticket) {
        TicketingJpaEntity toCreate = new TicketingJpaEntity();
        toCreate.setOwner(ticket.getOwner().uuid());
        toCreate.setAmount(ticket.getAmount());
        toCreate.setPit(ticket.getPit());
        ticketingRepository.save(toCreate);


    }

}

package be.kdg.prog6.parkManagement.core;

import be.kdg.prog6.parkManagement.domain.Guest;
import be.kdg.prog6.parkManagement.domain.Ticket;
import be.kdg.prog6.parkManagement.events.TicketingReceivedTickedEvent;
import be.kdg.prog6.parkManagement.ports.in.TicketPurchasedProjector;
import be.kdg.prog6.parkManagement.ports.out.TicketingProjectionPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultTicketPurchasedProjector implements TicketPurchasedProjector {
    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultTicketPurchasedProjector.class);


    private final TicketingProjectionPort ticketingProjectionPort;


    public DefaultTicketPurchasedProjector(TicketingProjectionPort ticketingProjectionPort) {
        this.ticketingProjectionPort = ticketingProjectionPort;
    }


    @Override
    public Optional<Ticket> project(TicketingReceivedTickedEvent event) {
        LOGGER.info("He bought a ticket");
        ticketingProjectionPort.saveTicket(new Ticket(event.guestUUID(),event.amount(),event.pit()));
        return Optional.empty();
    }
}

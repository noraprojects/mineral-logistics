package be.kdg.prog6.parkManagement.ports.in;

import be.kdg.prog6.parkManagement.domain.Ticket;
import be.kdg.prog6.parkManagement.events.TicketingReceivedTickedEvent;

import java.util.Optional;

public interface TicketPurchasedProjector {

    Optional<Ticket> project(TicketingReceivedTickedEvent event);

}

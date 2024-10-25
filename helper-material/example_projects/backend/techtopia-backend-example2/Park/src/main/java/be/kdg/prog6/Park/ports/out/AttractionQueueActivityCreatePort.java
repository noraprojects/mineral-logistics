package be.kdg.prog6.Park.ports.out;

import be.kdg.prog6.Park.domain.AttractionQueueActivity;
import be.kdg.prog6.Park.domain.Ticket;

public interface AttractionQueueActivityCreatePort {
    void createAttractionQueueActivity(Ticket.TicketUUID ticketUUID, AttractionQueueActivity attractionActivity);
}

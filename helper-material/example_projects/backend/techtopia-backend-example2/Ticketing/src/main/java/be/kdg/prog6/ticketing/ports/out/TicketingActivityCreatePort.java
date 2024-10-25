package be.kdg.prog6.ticketing.ports.out;

import be.kdg.prog6.ticketing.domain.Guest;
import be.kdg.prog6.ticketing.domain.Ticket;
import be.kdg.prog6.ticketing.domain.TicketingActivity;

public interface TicketingActivityCreatePort {
    void createTicketActivity(Guest.GuestUUID guestUUID, TicketingActivity ticketingActivity);

}

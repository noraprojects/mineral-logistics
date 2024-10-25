package be.kdg.prog6.ticketing.ports.out;

import be.kdg.prog6.ticketing.domain.DayPassTicket;
import be.kdg.prog6.ticketing.domain.Guest;
import be.kdg.prog6.ticketing.domain.Ticket;
import be.kdg.prog6.ticketing.domain.WeekPassTicket;

public interface TicketCreatePort {

    void weekTicketCreated(Guest.GuestUUID guest, WeekPassTicket ticket);
    void dayTicketCreated(Guest.GuestUUID guest, DayPassTicket ticket);

}

package be.kdg.prog6.ticketing.core;

import be.kdg.prog6.ticketing.domain.*;
import be.kdg.prog6.ticketing.ports.in.PurchaseTicketCommand;
import be.kdg.prog6.ticketing.ports.in.PurschaseTicketUseCase;
import be.kdg.prog6.ticketing.ports.out.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultPurschaseTicketUseCase implements PurschaseTicketUseCase {
    private final TicketCreatePort ticketCreatePort;
    private final TicketingLoadPort ticketingLoadPort;
    private final UserLoadPort userLoadPort;
    private final List<TicketingActivityCreatePort> ticketingActivityCreatePorts;
    private final TicketFactory ticketFactory;
    private final UserCreatePort userCreatePort;


    public DefaultPurschaseTicketUseCase(@Qualifier("ticketingDBAdapter") TicketCreatePort ticketCreatePort, TicketingLoadPort ticketingLoadPort, UserLoadPort userLoadPort, List<TicketingActivityCreatePort> ticketingActivityCreatePorts, TicketFactory ticketFactory, UserCreatePort userCreatePort) {
        this.ticketCreatePort = ticketCreatePort;
        this.ticketingLoadPort = ticketingLoadPort;
        this.userLoadPort = userLoadPort;
        this.ticketingActivityCreatePorts = ticketingActivityCreatePorts;
        this.ticketFactory = ticketFactory;
        this.userCreatePort = userCreatePort;
    }


    @Transactional
    @Override
    public void purchaseWeekTicket(PurchaseTicketCommand purschaseTicketCommand) {
        Optional<Guest> optionalGuest = userLoadPort.loadGuestByID(purschaseTicketCommand.guest());
        Guest guest = null;
        if (optionalGuest.isEmpty()) {
            guest = userCreatePort.createUser(purschaseTicketCommand.guest());
        } else {
            guest = optionalGuest.get();
        }
        assert guest != null;
        PricingStrategy strategy = new WeekPassPricingStrategy();
        WeekPassTicket ticket = ticketFactory.createWeekPass(guest.getSso(), purschaseTicketCommand.startDate(), purschaseTicketCommand.endDate(), strategy);
        ticketCreatePort.weekTicketCreated(guest.getSso(), ticket);
        TicketingActivity activity = ticket.createTicket(purschaseTicketCommand.price());
        ticketingActivityCreatePorts.forEach(port -> port.createTicketActivity(new Guest.GuestUUID(ticket.getOwner().uuid()), activity));
    }


    @Transactional
    @Override
    public void purchaseDayTicket(PurchaseTicketCommand purschaseTicketCommand) {


        Optional<Guest> optionalGuest = userLoadPort.loadGuestByID(purschaseTicketCommand.guest());
        Guest guest = null;
        if (optionalGuest.isEmpty()) {
            guest = userCreatePort.createUser(purschaseTicketCommand.guest());
        } else {
            guest = optionalGuest.get();
        }
        assert guest != null;

        PricingStrategy strategy = new DayPassPricingStrategy();

        DayPassTicket ticket = ticketFactory.createDayPass(guest.getSso(), LocalDate.now().plusDays(6), strategy);
        ticketCreatePort.dayTicketCreated(guest.getSso(), ticket);
        TicketingActivity activity = ticket.createTicket(purschaseTicketCommand.price());
        ticketingActivityCreatePorts.forEach(port -> port.createTicketActivity(new Guest.GuestUUID(ticket.getOwner().uuid()), activity));
    }
}

package be.kdg.prog6.ticketing.core;

import be.kdg.prog6.ticketing.adapters.out.db.TicketingJpaEntity;
import be.kdg.prog6.ticketing.domain.Guest;
import be.kdg.prog6.ticketing.ports.in.GetTicketCommand;
import be.kdg.prog6.ticketing.ports.in.GetTicketUseCase;
import be.kdg.prog6.ticketing.ports.out.TicketingActivityCreatePort;
import be.kdg.prog6.ticketing.ports.out.TicketingLoadPort;
import be.kdg.prog6.ticketing.ports.out.UserLoadPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultGetTicketUseCase implements GetTicketUseCase {

    private final TicketingLoadPort ticketingLoadPort;
    private final List<TicketingActivityCreatePort> ticketingActivityCreatePorts;
    private final UserLoadPort userLoadPort;

    public DefaultGetTicketUseCase(TicketingLoadPort ticketingLoadPort, List<TicketingActivityCreatePort> ticketingActivityCreatePorts, UserLoadPort userLoadPort) {
        this.ticketingLoadPort = ticketingLoadPort;
        this.ticketingActivityCreatePorts = ticketingActivityCreatePorts;
        this.userLoadPort = userLoadPort;
    }

    @Override
    public List<TicketingJpaEntity> getTicket(GetTicketCommand getTicketCommand) {
        Optional<Guest> guest = userLoadPort.loadGuestByID(getTicketCommand.guest());
        return ticketingLoadPort.loadTicketsByGuest(guest.get().getSso());
    }

}

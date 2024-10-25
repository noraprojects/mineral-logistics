package be.kdg.prog6.ticketing.adapters.out.db;

import be.kdg.prog6.ticketing.domain.*;
import be.kdg.prog6.ticketing.ports.out.TicketCreatePort;
import be.kdg.prog6.ticketing.ports.out.TicketingActivityCreatePort;
import be.kdg.prog6.ticketing.ports.out.TicketingActivityLoadPort;
import be.kdg.prog6.ticketing.ports.out.TicketingLoadPort;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class TicketingDBAdapter implements TicketingLoadPort, TicketingActivityLoadPort, TicketCreatePort, TicketingActivityCreatePort {

    private final TicketingRepository ticketingRepository;
    private final TicketingActivityRepository ticketingActivityRepository;

    public TicketingDBAdapter(TicketingRepository ticketingRepository, TicketingActivityRepository ticketingActivityRepository) {
        this.ticketingRepository = ticketingRepository;
        this.ticketingActivityRepository = ticketingActivityRepository;

    }

    @Override
    public void createTicketActivity(Guest.GuestUUID guestUUID, TicketingActivity ticketingActivity) {
        TicketingJpaActivity ticketingJpaActivity = new TicketingJpaActivity();
        ticketingJpaActivity.setAmount(ticketingActivity.amount());
        ticketingJpaActivity.setTicketingAction(ticketingActivity.action());
        ticketingJpaActivity.setPit(ticketingActivity.pit());
        ticketingJpaActivity.setTicket(guestUUID.uuid());
        ticketingActivityRepository.save(ticketingJpaActivity);
    }

    @Override
    public Optional<Ticket> loadTicket(Guest.GuestUUID guestUUID, LocalDateTime start, LocalDateTime end) {
        return Optional.empty();
    }

    @Override
    public List<TicketingJpaEntity> loadTicketsByGuest(Guest.GuestUUID uuid) {
        return ticketingRepository.findAllByOwner(uuid.uuid());
    }


    @Override
    public void weekTicketCreated(Guest.GuestUUID guest, WeekPassTicket ticket) {
        TicketingJpaEntity ticketingJpaEntity = new TicketingJpaEntity(ticket.getTicketUUID().uuid(), guest.uuid(), ticket.getStartDate(), ticket.getEndDate());
        ticketingRepository.save(ticketingJpaEntity);
    }

    @Override
    public void dayTicketCreated(Guest.GuestUUID guest, DayPassTicket ticket) {
        TicketingJpaEntity ticketingJpaEntity = new TicketingJpaEntity(ticket.getTicketUUID().uuid(), guest.uuid(), ticket.getVisitDate(), ticket.getVisitDate());
        ticketingRepository.save(ticketingJpaEntity);
    }


}

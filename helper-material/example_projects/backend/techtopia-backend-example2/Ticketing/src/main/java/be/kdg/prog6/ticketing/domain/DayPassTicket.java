package be.kdg.prog6.ticketing.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class DayPassTicket extends Ticket {
    private final LocalDate visitDate;



    public DayPassTicket(TicketUUID id, Guest.GuestUUID guest, LocalDate visitDate, Pricing pricing, PassType passType, ActivityWindow activityWindow) {
        super(id, guest, pricing,passType, activityWindow);
        this.visitDate = visitDate;

    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public boolean isValidOnDate(LocalDate date) {
        return !date.isBefore(visitDate) && !date.isAfter(visitDate);
    }
}

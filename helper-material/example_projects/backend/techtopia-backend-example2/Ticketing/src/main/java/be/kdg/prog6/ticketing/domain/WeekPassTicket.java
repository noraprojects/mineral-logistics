package be.kdg.prog6.ticketing.domain;

import java.time.LocalDate;

public class WeekPassTicket extends Ticket {

    private final LocalDate startDate;
    private final LocalDate endDate;


    public WeekPassTicket(TicketUUID id, Guest.GuestUUID guest, Pricing pricing, PassType passType, ActivityWindow activityWindow, LocalDate startDate, LocalDate endDate) {
        super(id, guest, pricing, passType, activityWindow);
        this.startDate = startDate;
        this.endDate = endDate;

    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }


    public boolean isValidOnDate(LocalDate date) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }
}

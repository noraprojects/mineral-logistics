package be.kdg.prog6.parkManagement.domain;

import be.kdg.prog6.Admission.domain.Guest;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {

    private final Guest.GuestUUID owner;
    private final LocalDateTime issueDate;
    private TicketUUID ticketUUID;

    public Ticket(String id, Guest guest, LocalDateTime issueDate) {
        this.owner = guest.getSso();
        this.issueDate = issueDate;
    }

    public Ticket(TicketUUID ticketUUID, Guest.GuestUUID sso) {
        this.ticketUUID = ticketUUID;
        this.owner = sso;
        this.issueDate = LocalDateTime.now();
    }

    public TicketUUID getTicketUUID() {
        return ticketUUID;
    }

    public Guest.GuestUUID getOwner() {
        return owner;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public record TicketUUID(UUID uuid) {
    }


}

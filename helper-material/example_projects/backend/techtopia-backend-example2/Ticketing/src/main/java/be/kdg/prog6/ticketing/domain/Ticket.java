package be.kdg.prog6.ticketing.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {

    private final Guest.GuestUUID owner;
    private final LocalDateTime issueDate;
    private final PassType passType;
    private TicketUUID ticketUUID;
    private Pricing pricing;
    private ActivityWindow activityWindow;


    public Ticket(TicketUUID ticketUUID, Guest.GuestUUID sso, Pricing pricing, PassType passType, ActivityWindow activityWindow) {
        this.ticketUUID = ticketUUID;
        this.owner = sso;
        this.issueDate = LocalDateTime.now();
        this.pricing = pricing;
        this.activityWindow = activityWindow;
        this.passType = passType;

    }

    public PassType getPassType() {
        return passType;
    }

    public TicketingActivity createTicket(BigDecimal money) {
        return new TicketingActivity(TicketingAction.purchased, money, LocalDateTime.now());
    }

    public ActivityWindow getActivityWindow() {
        return activityWindow;
    }

    public void setActivityWindow(ActivityWindow activityWindow) {
        this.activityWindow = activityWindow;
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

    public Pricing getPricing() {
        return pricing;
    }

    public void setPricing(Pricing pricing) {
        this.pricing = pricing;
    }

    public record TicketUUID(UUID uuid) {
    }
}

package be.kdg.prog6.Park.domain;
import java.time.LocalDateTime;

public record AttractionQueueActivity(
        QueueAction action,
        Ticket.TicketUUID ticketUUID, LocalDateTime pit, Attraction.AttractionUUID attractionUUID) {}

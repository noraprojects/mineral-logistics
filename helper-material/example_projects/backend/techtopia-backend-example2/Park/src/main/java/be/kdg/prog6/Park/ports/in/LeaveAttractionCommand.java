package be.kdg.prog6.Park.ports.in;

import be.kdg.prog6.Park.domain.Attraction;
import be.kdg.prog6.Park.domain.Ticket;

public record LeaveAttractionCommand(Ticket.TicketUUID tickerUUID, Attraction.AttractionUUID attractionUUID) {}

package be.kdg.prog6.parkManagement.domain;

import java.util.UUID;

public class Visitor {
    private String entryTime;
    private String exitTime;
    private ticketUUID ticketUUID;

    public record ticketUUID(UUID uuid) { }

    public Visitor( ticketUUID ticketUUID) {

        this.ticketUUID = ticketUUID;
    }
}

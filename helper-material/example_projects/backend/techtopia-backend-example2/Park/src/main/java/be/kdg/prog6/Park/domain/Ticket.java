package be.kdg.prog6.Park.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    private final UUID uid;
    private final PassType passType;
    private final LocalDateTime purchaseDate;
    private final LocalDateTime entryDate;

    public Ticket(PassType passType, LocalDateTime entryDate) {
        this.uid = UUID.randomUUID();
        this.passType = passType;
        this.purchaseDate = LocalDateTime.now();
        this.entryDate = entryDate;
    }

    public Ticket(UUID uid, PassType passType, LocalDateTime purchaseDate, LocalDateTime entryDate) {
        this.uid = uid;
        this.passType = passType;
        this.purchaseDate = purchaseDate;
        this.entryDate = entryDate;
    }

    public UUID getUid() {
        return uid;
    }

    public PassType getPassType() {
        return passType;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public enum PassType {
        DAY_PASS,
        WEEK_PASS
    }

    public record TicketUUID(UUID uuid) {
    }
}

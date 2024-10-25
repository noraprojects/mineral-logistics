package be.kdg.prog6.ticketing.adapters.out.db;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.NaturalId;

import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema="ticketing", name = "ticketing.tickets")
public class TicketingJpaEntity {

    @Id
    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;

    @Column
    @JdbcTypeCode(Types.VARCHAR)
    private UUID owner;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    public TicketingJpaEntity(UUID uuid, UUID owner, LocalDate startDate, LocalDate endDate) {
        this.uuid = uuid;
        this.owner = owner;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public TicketingJpaEntity() {
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public UUID getOwner() {
        return owner;
    }
}

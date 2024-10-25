package be.kdg.prog6.parkManagement.adapters.out.db;


import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.math.BigDecimal;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "ParkManagement", name = "parkmanagement_tickets")
public class TicketingJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @JdbcTypeCode(Types.VARCHAR)
    private UUID owner;

    @Column
    private BigDecimal amount;

    @Column
    private LocalDateTime pit;

    public TicketingJpaEntity() {
    }

    public Long getId() {
        return id;
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getPit() {
        return pit;
    }

    public void setPit(LocalDateTime pit) {
        this.pit = pit;
    }
}

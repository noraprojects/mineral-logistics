package be.kdg.prog6.ticketing.adapters.out.db;

import be.kdg.prog6.ticketing.domain.TicketingAction;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.math.BigDecimal;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(schema="ticketing",name = "ticketing.avtivities")
public class TicketingJpaActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID uuid;

    @Enumerated(EnumType.STRING)
    private TicketingAction ticketingAction;

    @Column(precision = 4)
    private BigDecimal amount;

    @JdbcTypeCode(Types.VARCHAR)
    private UUID ticket;

    private LocalDateTime pit;


    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public TicketingAction getTicketingAction() {
        return ticketingAction;
    }

    public void setTicketingAction(TicketingAction ticketingAction) {
        this.ticketingAction = ticketingAction;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public UUID getTicket() {
        return ticket;
    }

    public void setTicket(UUID ticket) {
        this.ticket = ticket;
    }

    public LocalDateTime getPit() {
        return pit;
    }

    public void setPit(LocalDateTime pit) {
        this.pit = pit;
    }
}

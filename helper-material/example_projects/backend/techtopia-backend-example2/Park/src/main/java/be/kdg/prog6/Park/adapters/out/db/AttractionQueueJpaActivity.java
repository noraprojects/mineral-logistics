package be.kdg.prog6.Park.adapters.out.db;

import be.kdg.prog6.Park.domain.QueueAction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.math.BigDecimal;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema="park",name = "park.activities")
public class AttractionQueueJpaActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private QueueAction ticketingAction;

    private LocalDateTime pit;


    @JdbcTypeCode(Types.VARCHAR)
    private UUID attraction;


    @JdbcTypeCode(Types.VARCHAR)
    private UUID owner;


    public QueueAction getTicketingAction() {
        return ticketingAction;
    }

    public void setTicketingAction(QueueAction ticketingAction) {
        this.ticketingAction = ticketingAction;
    }

    public UUID getAttraction() {
        return attraction;
    }

    public void setAttraction(UUID attraction) {
        this.attraction = attraction;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public LocalDateTime getPit() {
        return pit;
    }

    public void setPit(LocalDateTime pit) {
        this.pit = pit;
    }
}

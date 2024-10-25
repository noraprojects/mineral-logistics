package be.kdg.prog6.parkManagement.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Setter
@Getter
public class Ticket {

    private final Guest.GuestUUID owner;
    private final BigDecimal amount;
    private final LocalDateTime pit;



    public Ticket(Guest.GuestUUID sso, BigDecimal amount, LocalDateTime pit) {
        this.owner = sso;
        this.amount = amount;
        this.pit = pit;
    }

    public record TicketUUID(UUID uuid) {
    }









}

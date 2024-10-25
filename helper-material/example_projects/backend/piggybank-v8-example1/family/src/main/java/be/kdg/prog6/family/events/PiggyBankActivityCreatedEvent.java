package be.kdg.prog6.family.events;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PiggyBankActivityCreatedEvent(UUID piggyBankUUID, String action, BigDecimal amount,
                                            LocalDateTime pit) {

}
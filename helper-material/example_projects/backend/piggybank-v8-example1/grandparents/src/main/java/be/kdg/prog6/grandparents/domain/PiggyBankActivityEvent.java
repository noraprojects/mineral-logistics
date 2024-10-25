package be.kdg.prog6.grandparents.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PiggyBankActivityEvent(BigDecimal amount, PiggyBankAction action, LocalDateTime pit) {

}

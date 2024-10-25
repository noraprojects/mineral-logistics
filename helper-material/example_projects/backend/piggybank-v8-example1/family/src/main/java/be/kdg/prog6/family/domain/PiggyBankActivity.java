package be.kdg.prog6.family.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PiggyBankActivity(PiggyBankAction action, BigDecimal amount, LocalDateTime pit) {



}

package be.kdg.prog6.parkManagement.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record VisitorActivity(VisitorAction action, UUID user, LocalDateTime pit) {



}

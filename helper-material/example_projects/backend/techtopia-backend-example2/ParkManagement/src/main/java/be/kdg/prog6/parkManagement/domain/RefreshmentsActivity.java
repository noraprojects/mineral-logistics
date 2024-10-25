package be.kdg.prog6.parkManagement.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public record RefreshmentsActivity(RefreshmentStandAction action, UUID uuid, LocalDateTime pit) {
}

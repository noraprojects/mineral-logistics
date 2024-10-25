package be.kdg.prog6.parkManagement.events;

import java.util.UUID;

public record AttractionQueueReachedEvent(UUID attraction) implements Event{}

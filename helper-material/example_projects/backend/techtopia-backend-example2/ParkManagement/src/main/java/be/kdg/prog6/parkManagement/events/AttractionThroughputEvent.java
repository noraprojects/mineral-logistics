package be.kdg.prog6.parkManagement.events;

import be.kdg.prog6.parkManagement.domain.Attraction;

import java.util.UUID;

public record AttractionThroughputEvent(UUID attractionID) implements Event{
}

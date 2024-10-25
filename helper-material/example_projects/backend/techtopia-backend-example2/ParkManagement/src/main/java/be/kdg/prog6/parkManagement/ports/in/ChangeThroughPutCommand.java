package be.kdg.prog6.parkManagement.ports.in;

import be.kdg.prog6.parkManagement.domain.Attraction;

public record ChangeThroughPutCommand(Attraction.AttractionUUID attractionID) {
}


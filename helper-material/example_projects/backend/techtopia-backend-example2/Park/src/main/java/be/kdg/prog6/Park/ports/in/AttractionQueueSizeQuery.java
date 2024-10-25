package be.kdg.prog6.Park.ports.in;

import be.kdg.prog6.Park.domain.Attraction;

public interface AttractionQueueSizeQuery {

    int getQueueSizeForAttraction(Attraction.AttractionUUID attractionUUID);
}

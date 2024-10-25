package be.kdg.prog6.parkManagement.ports.out;

import be.kdg.prog6.parkManagement.domain.Attraction;

//
public interface AttractionUpdatePort {
    void updateThroughput(Attraction.AttractionUUID attractionUUID);
}

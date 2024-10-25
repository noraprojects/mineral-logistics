package be.kdg.prog6.Park.ports.out;

import be.kdg.prog6.Park.domain.Attraction;

import java.util.List;
import java.util.Optional;

public interface AttractionQueueLoadPort {

    Optional<Attraction> loadAttractionByUuid(Attraction.AttractionUUID uuid);

    List<Attraction> loadAllAttractions();
}

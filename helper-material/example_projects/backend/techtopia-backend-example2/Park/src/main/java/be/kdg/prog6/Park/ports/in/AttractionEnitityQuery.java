package be.kdg.prog6.Park.ports.in;

import be.kdg.prog6.Park.domain.Attraction;

import java.util.List;

public interface AttractionEnitityQuery {
    Attraction getAttraction(Attraction.AttractionUUID uuid);
    List<Attraction> getAllAttractions();
}

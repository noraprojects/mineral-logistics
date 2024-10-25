package be.kdg.prog6.Park.ports.out;

import be.kdg.prog6.Park.domain.Attraction;

public interface AttractionCreatePort {

    void attractionQeueuReached(Attraction attraction);
}

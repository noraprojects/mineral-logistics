package be.kdg.prog6.parkManagement.ports.in;
import be.kdg.prog6.parkManagement.domain.Attraction;
import be.kdg.prog6.parkManagement.events.AttractionQueueReachedEvent;


import java.util.Optional;

public interface QueueReachedProjector {
    Optional<Attraction> project(AttractionQueueReachedEvent event);
}

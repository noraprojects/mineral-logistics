package be.kdg.prog6.parkManagement.core;

import be.kdg.prog6.parkManagement.domain.Attraction;
import be.kdg.prog6.parkManagement.events.AttractionQueueReachedEvent;
import be.kdg.prog6.parkManagement.ports.in.QueueReachedProjector;
import be.kdg.prog6.parkManagement.ports.out.AttractionUpdatePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultQueueReachedProjector implements QueueReachedProjector {
    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultQueueReachedProjector.class);
//
    private final AttractionUpdatePort attractionUpdatePort;

    public DefaultQueueReachedProjector(AttractionUpdatePort attractionUpdatePort) {
        this.attractionUpdatePort = attractionUpdatePort;
    }

    @Override
    public Optional<Attraction> project(AttractionQueueReachedEvent event) {
        LOGGER.info("He Queue has received by park management");
        attractionUpdatePort.updateThroughput(new Attraction.AttractionUUID(event.attraction()));

        return Optional.empty();
    }
}

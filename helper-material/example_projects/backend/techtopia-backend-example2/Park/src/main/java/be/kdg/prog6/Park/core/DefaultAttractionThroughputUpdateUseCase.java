package be.kdg.prog6.Park.core;

import be.kdg.prog6.Park.domain.Attraction;
import be.kdg.prog6.Park.events.ParkReceivedThroughputEvent;
import be.kdg.prog6.Park.ports.in.AttractionThroughputUpdateUseCase;
import be.kdg.prog6.Park.ports.out.AttractionThroughputUpdatePort;
import org.springframework.stereotype.Service;

@Service
public class DefaultAttractionThroughputUpdateUseCase implements AttractionThroughputUpdateUseCase {

    AttractionThroughputUpdatePort attractionThroughputUpdatePort;

    public DefaultAttractionThroughputUpdateUseCase(AttractionThroughputUpdatePort attractionThroughputUpdatePort) {
        this.attractionThroughputUpdatePort = attractionThroughputUpdatePort;
    }

    @Override
    public void update(ParkReceivedThroughputEvent command) {
        attractionThroughputUpdatePort.update(new Attraction.AttractionUUID(command.attractionID()));
    }
}

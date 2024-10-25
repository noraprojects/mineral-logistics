package be.kdg.prog6.Park.core;

import be.kdg.prog6.Park.domain.Attraction;
import be.kdg.prog6.Park.ports.in.AttractionQueueSizeQuery;
import be.kdg.prog6.Park.ports.out.AttractionQueueActivityCreatePort;
import be.kdg.prog6.Park.ports.out.AttractionQueueLoadPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultAttractionQueueQuery implements AttractionQueueSizeQuery {

    private final AttractionQueueLoadPort attractionQueueLoadPort;


    public DefaultAttractionQueueQuery(AttractionQueueLoadPort attractionQueueLoadPort, AttractionQueueActivityCreatePort attractionQueueActivityCreatePort) {
        this.attractionQueueLoadPort = attractionQueueLoadPort;
    }

    @Override
    public int getQueueSizeForAttraction(Attraction.AttractionUUID attractionUUID) {
        Optional<Attraction> attractionOpt = attractionQueueLoadPort.loadAttractionByUuid(attractionUUID);
        if (attractionOpt.isPresent()) {
            Attraction attraction = attractionOpt.get();
            return attraction.getVisitorCount();
        } else {
            return 0;
        }
    }
}

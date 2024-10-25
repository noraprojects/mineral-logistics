package be.kdg.prog6.Park.core;

import be.kdg.prog6.Park.domain.Attraction;
import be.kdg.prog6.Park.ports.in.AttractionEnitityQuery;
import be.kdg.prog6.Park.ports.out.AttractionQueueLoadPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultAttractionEnitityQuery implements AttractionEnitityQuery {
    AttractionQueueLoadPort attractionQueueLoadPort;

    public DefaultAttractionEnitityQuery(AttractionQueueLoadPort attractionQueueLoadPort) {
        this.attractionQueueLoadPort = attractionQueueLoadPort;
    }

    @Override
    public Attraction getAttraction(Attraction.AttractionUUID uuid) {
        return attractionQueueLoadPort.loadAttractionByUuid(uuid).get();
    }

    @Override
    public List<Attraction> getAllAttractions() {
        return attractionQueueLoadPort.loadAllAttractions();
    }
}

package be.kdg.prog6.Park.core;

import be.kdg.prog6.Park.domain.AttractionQueueActivity;
import be.kdg.prog6.Park.ports.in.AttractionActivityQuery;
import be.kdg.prog6.Park.ports.out.AttractionActivityLoadPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultAttractionActivityQuery implements AttractionActivityQuery {

    private final AttractionActivityLoadPort attractionActivityLoadPort;

    public DefaultAttractionActivityQuery(AttractionActivityLoadPort attractionActivityLoadPort) {
        this.attractionActivityLoadPort = attractionActivityLoadPort;
    }

    @Override
    public List<AttractionQueueActivity> getActivities() {
        return attractionActivityLoadPort.getActivities();
    }
}

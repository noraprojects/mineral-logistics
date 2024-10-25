package be.kdg.prog6.Park.adapters.in;

import be.kdg.prog6.Park.adapters.in.web.HandlingException;
import be.kdg.prog6.Park.events.Event;
import be.kdg.prog6.Park.events.ParkDeleteStandEvent;
import be.kdg.prog6.Park.events.ParkReceivedStandEvent;
import be.kdg.prog6.Park.ports.in.StandDeletedProjector;
import be.kdg.prog6.Park.ports.in.StandReceivedProjector;
import be.kdg.prog6.common.events.EventCatalog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;


@Component
public class ParkStandDeleteAmqpAdapter implements ParkEventHandler<ParkDeleteStandEvent>{

    private final StandDeletedProjector standDeletedProjector;
    private final ObjectMapper objectMapper;


    public ParkStandDeleteAmqpAdapter(StandDeletedProjector standDeletedProjector, ObjectMapper objectMapper) {
        this.standDeletedProjector = standDeletedProjector;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean appliesTo(EventCatalog eventCatalog) {
        return EventCatalog.REFRESHMENT_STAND_REMOVED == eventCatalog;
    }

    @Override
    public Event map(String eventBody) {
        System.out.println("Handling event stands: " + eventBody);

        try {
            return objectMapper.readValue(eventBody, ParkDeleteStandEvent.class);
        } catch (JsonProcessingException e) {
            throw new HandlingException(e);
        }
    }

    @Override
    public void handle(Event eventBody) {
        standDeletedProjector.project((ParkDeleteStandEvent) eventBody);

    }
}

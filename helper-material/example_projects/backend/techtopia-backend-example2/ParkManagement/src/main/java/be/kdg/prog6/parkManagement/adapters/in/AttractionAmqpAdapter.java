package be.kdg.prog6.parkManagement.adapters.in;

import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.parkManagement.events.AttractionQueueReachedEvent;
import be.kdg.prog6.parkManagement.events.Event;
import be.kdg.prog6.parkManagement.ports.in.QueueReachedProjector;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;


@Component
public class AttractionAmqpAdapter implements ParkManagementEventHandler<AttractionQueueReachedEvent> {

    private final QueueReachedProjector queueReachedProjector;
    private final ObjectMapper objectMapper;

    public AttractionAmqpAdapter(QueueReachedProjector queueReachedProjector, ObjectMapper objectMapper) {
        this.queueReachedProjector = queueReachedProjector;
        this.objectMapper = objectMapper;
    }


    @Override
    public boolean appliesTo(EventCatalog eventCatalog) {
        return EventCatalog.ATTRACTION_QUEUE_REACHED == eventCatalog;
    }

    @Override
    public Event map(String eventBody) {
        try {
            return objectMapper.readValue(eventBody, AttractionQueueReachedEvent.class);
        } catch (JsonProcessingException e) {
            throw new HandlingException(e);
        }
    }

    @Override
    public void handle(Event eventBody) {
        queueReachedProjector.project((AttractionQueueReachedEvent) eventBody);

    }
}

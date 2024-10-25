package be.kdg.prog6.Park.adapters.in;

import be.kdg.prog6.Park.adapters.in.web.HandlingException;
import be.kdg.prog6.Park.events.Event;
import be.kdg.prog6.Park.events.ParkReceivedThroughputEvent;
import be.kdg.prog6.Park.ports.in.AttractionThroughputUpdateUseCase;
import be.kdg.prog6.common.events.EventCatalog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class AttractionThroughputAmqpAdapter implements ParkEventHandler<ParkReceivedThroughputEvent> {
    private final ObjectMapper objectMapper;
    AttractionThroughputUpdateUseCase attractionThroughputUpdateUseCase;


    public AttractionThroughputAmqpAdapter(ObjectMapper objectMapper, AttractionThroughputUpdateUseCase attractionThroughputUpdateUseCase) {
        this.attractionThroughputUpdateUseCase = attractionThroughputUpdateUseCase;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean appliesTo(EventCatalog eventCatalog) {
        return EventCatalog.ATTRACTION_THROUGHPUT_UPDATE == eventCatalog;
    }

    @Override
    public ParkReceivedThroughputEvent map(String eventBody) {
        System.out.println("Handling event stands: " + eventBody);

        try {
            return objectMapper.readValue(eventBody, ParkReceivedThroughputEvent.class);
        } catch (JsonProcessingException e) {
            throw new HandlingException(e);
        }
    }

    @Override
    public void handle(Event eventBody) {
        System.out.println("handing the handle");
        System.out.println(eventBody);
        attractionThroughputUpdateUseCase.update((ParkReceivedThroughputEvent) eventBody);

    }
}

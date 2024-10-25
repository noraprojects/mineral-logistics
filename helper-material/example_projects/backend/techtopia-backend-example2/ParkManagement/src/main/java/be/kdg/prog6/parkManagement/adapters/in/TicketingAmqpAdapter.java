package be.kdg.prog6.parkManagement.adapters.in;

import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.parkManagement.events.Event;
import be.kdg.prog6.parkManagement.events.TicketingReceivedTickedEvent;
import be.kdg.prog6.parkManagement.ports.in.TicketPurchasedProjector;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class TicketingAmqpAdapter implements ParkManagementEventHandler<TicketingReceivedTickedEvent> {


    private final TicketPurchasedProjector ticketPurchasedProjector;
    private final ObjectMapper objectMapper;

    public TicketingAmqpAdapter(TicketPurchasedProjector ticketPurchasedProjector, ObjectMapper objectMapper) {
        this.ticketPurchasedProjector = ticketPurchasedProjector;
        this.objectMapper = objectMapper;
    }


    @Override
    public boolean appliesTo(EventCatalog eventCatalog) {
        return EventCatalog.TICKETING_RECEIVED_TICKET == eventCatalog;
    }


    @Override
    public TicketingReceivedTickedEvent map(String eventBody) {
        System.out.println("Handling event: " + eventBody);
        try {
            return objectMapper.readValue(eventBody, TicketingReceivedTickedEvent.class);
        } catch (JsonProcessingException e) {
            throw new HandlingException(e);
        }
    }

    @Override
    public void handle(Event eventBody) {
        ticketPurchasedProjector.project((TicketingReceivedTickedEvent) eventBody);
    }
}

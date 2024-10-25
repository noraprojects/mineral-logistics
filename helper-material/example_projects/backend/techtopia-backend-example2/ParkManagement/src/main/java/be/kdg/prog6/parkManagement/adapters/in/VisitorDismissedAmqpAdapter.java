package be.kdg.prog6.parkManagement.adapters.in;


import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.parkManagement.events.Event;
import be.kdg.prog6.parkManagement.events.UserDismissedActivityEvent;
import be.kdg.prog6.parkManagement.ports.in.UserDissmissProjector;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class VisitorDismissedAmqpAdapter implements ParkManagementEventHandler<UserDismissedActivityEvent> {

    private final UserDissmissProjector userDissmissProjector;
    private final ObjectMapper objectMapper;

    public VisitorDismissedAmqpAdapter(UserDissmissProjector userDissmissProjector, ObjectMapper objectMapper) {
        this.userDissmissProjector = userDissmissProjector;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean appliesTo(EventCatalog eventCatalog) {
        return EventCatalog.VISITOR_DISMISSED == eventCatalog;

    }

    @Override
    public Event map(String eventBody) {
        System.out.println("Handling event: " + eventBody);
        try {
            return objectMapper.readValue(eventBody, UserDismissedActivityEvent.class);
        } catch (JsonProcessingException e) {
            throw new HandlingException(e);
        }
    }

    @Override
    public void handle(Event eventBody) {
        userDissmissProjector.dismissUser((UserDismissedActivityEvent) eventBody);


    }
}

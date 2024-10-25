package be.kdg.prog6.parkManagement.adapters.in;

import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.parkManagement.events.Event;
import be.kdg.prog6.parkManagement.events.UserAdmittedActivityEvent;
import be.kdg.prog6.parkManagement.ports.in.UserAdmittedProjector;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class VisitorAdmittedAmqpAdapter implements ParkManagementEventHandler<UserAdmittedActivityEvent> {

    private final UserAdmittedProjector userAdmittedProjector;
    private final ObjectMapper objectMapper;

    public VisitorAdmittedAmqpAdapter(UserAdmittedProjector userAdmittedProjector, ObjectMapper objectMapper) {
        this.userAdmittedProjector = userAdmittedProjector;
        this.objectMapper = objectMapper;
    }


    @Override
    public boolean appliesTo(EventCatalog eventCatalog) {
        return EventCatalog.VISITOR_ADMITTED == eventCatalog;
    }

    @Override
    public Event map(String eventBody) {
        System.out.println("Handling event: " + eventBody);
        try {
            return objectMapper.readValue(eventBody, UserAdmittedActivityEvent.class);
        } catch (JsonProcessingException e) {
            throw new HandlingException(e);
        }
    }

    @Override
    public void handle(Event eventBody) {
        userAdmittedProjector.admitUser((UserAdmittedActivityEvent) eventBody);

    }
}

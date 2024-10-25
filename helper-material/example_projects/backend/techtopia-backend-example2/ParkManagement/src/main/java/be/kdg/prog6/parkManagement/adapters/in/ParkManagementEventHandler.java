package be.kdg.prog6.parkManagement.adapters.in;

import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.common.events.EventMessage;
import be.kdg.prog6.parkManagement.events.Event;

public interface ParkManagementEventHandler<T extends Event>{

    boolean appliesTo(EventCatalog eventCatalog);

    default ParkManagementEventHandler<T> receive(EventMessage eventMessage) {
        return this;
    }

    Event map(String eventBody);

    void handle(Event eventBody);

}

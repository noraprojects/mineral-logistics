package be.kdg.prog6.grandparents.adapters.in;

import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.common.events.EventMessage;
import be.kdg.prog6.grandparents.events.Event;

public interface PiggyBankEventHandler<T extends Event> {

    boolean appliesTo(EventCatalog eventCatalog);

    default PiggyBankEventHandler<T> receive(EventMessage eventMessage) {
        //check if this is a duplicate message in the eventstore
        //if not handle the event
        return this;
    }

    Event map(String eventBody);

    void handle(Event eventBody);


}

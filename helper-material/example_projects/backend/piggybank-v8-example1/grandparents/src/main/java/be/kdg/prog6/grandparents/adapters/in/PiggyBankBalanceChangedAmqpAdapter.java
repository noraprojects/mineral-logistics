package be.kdg.prog6.grandparents.adapters.in;

import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.grandparents.events.Event;
import be.kdg.prog6.grandparents.events.PiggyBankActivityCreatedEvent;
import be.kdg.prog6.grandparents.ports.in.CheckBalanceUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class PiggyBankBalanceChangedAmqpAdapter implements PiggyBankEventHandler<PiggyBankActivityCreatedEvent> {

    private final CheckBalanceUseCase checkBalanceUseCase;
    private final ObjectMapper objectMapper;

    public PiggyBankBalanceChangedAmqpAdapter(CheckBalanceUseCase checkBalanceUseCase, ObjectMapper objectMapper, ObjectMapper objectMapper1) {
        this.checkBalanceUseCase = checkBalanceUseCase;
        this.objectMapper = objectMapper1;
    }

    @Override
    public boolean appliesTo(EventCatalog eventCatalog) {
        return EventCatalog.PIGGYBANK_ACTIVITY_CREATED == eventCatalog;
    }

    @Override
    public PiggyBankActivityCreatedEvent map(String eventBody) {
        System.out.println("Handling event: " + eventBody);
        try {
            return objectMapper.readValue(eventBody, PiggyBankActivityCreatedEvent.class);
        } catch (JsonProcessingException e) {
            throw new HandlingException(e);
        }
    }

    @Override
    public void handle(Event eventBody) {
        checkBalanceUseCase.checkBalance((PiggyBankActivityCreatedEvent) eventBody);
    }
}

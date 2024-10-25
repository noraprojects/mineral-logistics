package be.kdg.prog6.grandparents.adapters.in;

import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.grandparents.events.Event;
import be.kdg.prog6.grandparents.events.FamilyReceivedPiggyBankEvent;
import be.kdg.prog6.grandparents.ports.in.FamilyMemberPiggyBankOwnerProjector;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class FamilyMemberPiggyBankAmqpAdapter implements PiggyBankEventHandler<FamilyReceivedPiggyBankEvent> {

    private final FamilyMemberPiggyBankOwnerProjector familyMemberPiggyBankOwnerProjector;
    private final ObjectMapper objectMapper;

    public FamilyMemberPiggyBankAmqpAdapter(FamilyMemberPiggyBankOwnerProjector familyMemberPiggyBankOwnerProjector, ObjectMapper objectMapper) {
        this.familyMemberPiggyBankOwnerProjector = familyMemberPiggyBankOwnerProjector;
        this.objectMapper = objectMapper;
    }


    @Override
    public boolean appliesTo(EventCatalog eventCatalog) {
        return EventCatalog.FAMILY_RECEIVED_PIGGYBANK == eventCatalog;
    }

    @Override
    public FamilyReceivedPiggyBankEvent map(String eventBody) {
        System.out.println("Handling event: " + eventBody);
        try {
            return objectMapper.readValue(eventBody, FamilyReceivedPiggyBankEvent.class);
        } catch (JsonProcessingException e) {
            throw new HandlingException(e);
        }
    }

    @Override
    public void handle(Event eventBody) {
        familyMemberPiggyBankOwnerProjector.project((FamilyReceivedPiggyBankEvent) eventBody);
    }
}

package be.kdg.prog6.family.adapters.out.amqp;

import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.common.events.EventHeader;
import be.kdg.prog6.common.events.EventMessage;
import be.kdg.prog6.family.adapters.config.RabbitMQModuleTopology;
import be.kdg.prog6.family.domain.PiggyBank;
import be.kdg.prog6.family.events.FamilyReceivedPiggyBankEvent;
import be.kdg.prog6.family.ports.out.PiggyBankCreatePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PiggyBankCreateAMQPPublisher implements PiggyBankCreatePort {

    public static final Logger log = LoggerFactory.getLogger(PiggyBankCreateAMQPPublisher.class);

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;


    public PiggyBankCreateAMQPPublisher(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }


    @Override
    public void piggyBankCreated(String owner, PiggyBank piggyBank) {
        log.info("PUblishing event that new piggybank is created");
        var eventHeader = EventHeader.builder().eventID(UUID.randomUUID()).eventCatalog(EventCatalog.FAMILY_RECEIVED_PIGGYBANK).build();
        var eventBody = new FamilyReceivedPiggyBankEvent(piggyBank.getOwner().uuid(), owner, piggyBank.getPiggyBankUUID().uuid());
        try {
            rabbitTemplate.convertAndSend(RabbitMQModuleTopology.PIGGY_BANK_EVENTS_FAN_OUT, "piggybank.created", EventMessage.builder().eventHeader(eventHeader).eventBody(objectMapper.writeValueAsString(eventBody)).build());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

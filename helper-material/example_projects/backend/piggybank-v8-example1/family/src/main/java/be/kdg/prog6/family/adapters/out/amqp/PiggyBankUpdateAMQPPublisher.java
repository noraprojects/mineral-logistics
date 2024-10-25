package be.kdg.prog6.family.adapters.out.amqp;

import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.common.events.EventHeader;
import be.kdg.prog6.common.events.EventMessage;
import be.kdg.prog6.family.adapters.config.RabbitMQModuleTopology;
import be.kdg.prog6.family.domain.PiggyBank;
import be.kdg.prog6.family.domain.PiggyBankActivity;
import be.kdg.prog6.family.events.PiggyBankActivityCreatedEvent;
import be.kdg.prog6.family.ports.out.PiggyBankActivityCreatePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class PiggyBankUpdateAMQPPublisher implements PiggyBankActivityCreatePort {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;


    public PiggyBankUpdateAMQPPublisher(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void createPiggyBankActivity(PiggyBank.PiggyBankUUID piggyBankUUID, PiggyBankActivity piggyBankActivity) {
        System.out.println(">>>>>>>>> Publishing event to RabbitMQ!!!");
        var header = EventHeader.builder().eventID(UUID.randomUUID()).eventCatalog(EventCatalog.PIGGYBANK_ACTIVITY_CREATED).build();
        var eventBody = new PiggyBankActivityCreatedEvent(piggyBankUUID.uuid(), piggyBankActivity.action().name(), piggyBankActivity.amount(), LocalDateTime.now());
        EventMessage eventMessage = null;
        try {
            eventMessage = EventMessage.builder().eventHeader(header).eventBody(objectMapper.writeValueAsString(eventBody)).build();
            rabbitTemplate.convertAndSend(RabbitMQModuleTopology.PIGGY_BANK_EVENTS_FAN_OUT, "piggybank.activity.created", eventMessage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

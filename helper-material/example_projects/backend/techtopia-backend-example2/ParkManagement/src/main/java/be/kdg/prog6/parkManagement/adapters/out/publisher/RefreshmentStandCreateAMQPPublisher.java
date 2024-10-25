package be.kdg.prog6.parkManagement.adapters.out.publisher;

import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.common.events.EventHeader;
import be.kdg.prog6.common.events.EventMessage;
import be.kdg.prog6.parkManagement.adapters.config.RabbitMQTopology;
import be.kdg.prog6.parkManagement.events.ParkManagementReceivedRefreshmentStandEvent;
import be.kdg.prog6.parkManagement.ports.out.RefreshmentStandCreatePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RefreshmentStandCreateAMQPPublisher implements RefreshmentStandCreatePort {
    public static final Logger log = LoggerFactory.getLogger(RefreshmentStandCreateAMQPPublisher.class);

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public RefreshmentStandCreateAMQPPublisher(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void refreshmentStandCreated(UUID standUUID) {
        log.info("A new refreshments stand was created");
        var eventHeader = EventHeader.builder().eventID(UUID.randomUUID()).eventCatalog(EventCatalog.REFRESHMENT_STAND_CREATED).build();
        var eventBody = new ParkManagementReceivedRefreshmentStandEvent(standUUID);
        try {
            rabbitTemplate.convertAndSend(RabbitMQTopology.STAND_EVENTS_FAN_OUT, "stand.created", EventMessage.builder().eventHeader(eventHeader).eventBody(objectMapper.writeValueAsString(eventBody)).build());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void refreshmentStandDeleted(UUID standUUID) {
        log.info("A new refreshments stand was deleted");
        var eventHeader = EventHeader.builder().eventID(UUID.randomUUID()).eventCatalog(EventCatalog.REFRESHMENT_STAND_REMOVED).build();
        var eventBody = new ParkManagementReceivedRefreshmentStandEvent(standUUID);
        try {
            rabbitTemplate.convertAndSend(RabbitMQTopology.STAND_EVENTS_FAN_OUT, "stand.created", EventMessage.builder().eventHeader(eventHeader).eventBody(objectMapper.writeValueAsString(eventBody)).build());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

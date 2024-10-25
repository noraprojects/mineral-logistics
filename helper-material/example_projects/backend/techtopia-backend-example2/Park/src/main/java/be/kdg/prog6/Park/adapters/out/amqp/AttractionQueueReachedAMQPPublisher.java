package be.kdg.prog6.Park.adapters.out.amqp;

import be.kdg.prog6.Park.adapters.config.RabbitMQTopology;
import be.kdg.prog6.Park.domain.Attraction;
import be.kdg.prog6.Park.events.AttractionQeueuReachedEvent;
import be.kdg.prog6.Park.ports.out.AttractionCreatePort;
import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.common.events.EventHeader;
import be.kdg.prog6.common.events.EventMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AttractionQueueReachedAMQPPublisher implements AttractionCreatePort {
    public static final Logger log = LoggerFactory.getLogger(AttractionQueueReachedAMQPPublisher.class);

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;



    public AttractionQueueReachedAMQPPublisher(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void attractionQeueuReached(Attraction attraction) {
        log.info("Publishing event that new Queue has reached 80!");
        var eventHeader = EventHeader.builder().eventID(UUID.randomUUID()).eventCatalog(EventCatalog.ATTRACTION_QUEUE_REACHED).build();
        var eventBody = new AttractionQeueuReachedEvent(attraction.getAttractionUUID().uuid());
        try {
            rabbitTemplate.convertAndSend(RabbitMQTopology.QUEUE_REACHED_EVENTS_FAN_OUT, "queue_reached.created", EventMessage.builder().eventHeader(eventHeader).eventBody(objectMapper.writeValueAsString(eventBody)).build());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

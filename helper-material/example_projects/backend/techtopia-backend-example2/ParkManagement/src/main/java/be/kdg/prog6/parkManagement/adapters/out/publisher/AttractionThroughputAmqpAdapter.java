package be.kdg.prog6.parkManagement.adapters.out.publisher;

import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.common.events.EventHeader;
import be.kdg.prog6.common.events.EventMessage;
import be.kdg.prog6.parkManagement.adapters.config.RabbitMQTopology;
import be.kdg.prog6.parkManagement.domain.Attraction;
import be.kdg.prog6.parkManagement.events.AttractionThroughputEvent;
import be.kdg.prog6.parkManagement.ports.out.AttractionUpdatePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AttractionThroughputAmqpAdapter implements AttractionUpdatePort {

    public static final Logger log = LoggerFactory.getLogger(AttractionThroughputAmqpAdapter.class);

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public AttractionThroughputAmqpAdapter(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }


    @Override
    public void updateThroughput(Attraction.AttractionUUID attractionUUID) {
        log.info("CHANGE THE THORUGPUT!!! parbo");
        var eventHeader = EventHeader.builder().eventID(UUID.randomUUID()).eventCatalog(EventCatalog.ATTRACTION_THROUGHPUT_UPDATE).build();
        var eventBody = new AttractionThroughputEvent(attractionUUID.uuid());
        try {
            rabbitTemplate.convertAndSend(RabbitMQTopology.ATTRACTION_THROUGHPUT_FAN_OUT, "queue_reached.created", EventMessage.builder().eventHeader(eventHeader).eventBody(objectMapper.writeValueAsString(eventBody)).build());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}


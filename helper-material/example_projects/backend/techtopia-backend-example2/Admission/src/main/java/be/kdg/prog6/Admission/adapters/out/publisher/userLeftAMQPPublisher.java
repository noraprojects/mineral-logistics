package be.kdg.prog6.Admission.adapters.out.publisher;

import be.kdg.prog6.Admission.adapters.config.RabbitMQTopology;
import be.kdg.prog6.Admission.events.LeaveParkEvent;
import be.kdg.prog6.Admission.ports.out.LeaveParkPort;
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
public class userLeftAMQPPublisher implements LeaveParkPort {

    public static final Logger log = LoggerFactory.getLogger(userLeftAMQPPublisher.class);

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public userLeftAMQPPublisher(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void leavePark(String code) {
        log.info("Leaving the park " + code);

        var eventHeader = EventHeader.builder()
                .eventID(UUID.randomUUID())
                .eventCatalog(EventCatalog.VISITOR_DISMISSED)
                .build();

        var eventBody = new LeaveParkEvent(code);

        try {
            rabbitTemplate.convertAndSend(
                    RabbitMQTopology.ADMISSION_EVENTS_FAN_OUT,
                    "admission.created",
                    EventMessage.builder()
                            .eventHeader(eventHeader)
                            .eventBody(objectMapper.writeValueAsString(eventBody))
                            .build()
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}

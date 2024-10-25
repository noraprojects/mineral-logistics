package be.kdg.prog6.Admission.adapters.out.publisher;

import be.kdg.prog6.Admission.adapters.config.RabbitMQTopology;
import be.kdg.prog6.Admission.domain.TicketCodeUtil;
import be.kdg.prog6.Admission.events.TicketingReceivedTickedEvent;
import be.kdg.prog6.Admission.ports.out.TicketValidatorPort;
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
public class userEnteredAMQPPublisher implements TicketValidatorPort {
    public static final Logger log = LoggerFactory.getLogger(userEnteredAMQPPublisher.class);
    
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public userEnteredAMQPPublisher(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean validateTicket(String code) {
        log.info("Received ticket code: " + code);

        // Validate the ticket code
        boolean isValidCode = TicketCodeUtil.isValidUUID(code);

        if (isValidCode) {
            log.info("Valid ticket code received. Publishing event.");

            var eventHeader = EventHeader.builder()
                    .eventID(UUID.randomUUID())
                    .eventCatalog(EventCatalog.VISITOR_ADMITTED)
                    .build();

            var eventBody = new TicketingReceivedTickedEvent(code);

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

            return true;
        } else {
            log.warn("Invalid ticket code received. No event will be published.");
            return false;
        }
    }

}

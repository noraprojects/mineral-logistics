package be.kdg.prog6.ticketing.adapters.out.amqp;

import be.kdg.prog6.common.events.EventCatalog;
import be.kdg.prog6.common.events.EventHeader;
import be.kdg.prog6.common.events.EventMessage;
import be.kdg.prog6.ticketing.adapters.config.RabbitMQTopology;
import be.kdg.prog6.ticketing.domain.Guest;
import be.kdg.prog6.ticketing.domain.TicketingActivity;
import be.kdg.prog6.ticketing.events.TicketingReceivedTicketEvent;
import be.kdg.prog6.ticketing.ports.out.TicketingActivityCreatePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TicketCreateAMQPPublisher implements TicketingActivityCreatePort {

    public static final Logger log = LoggerFactory.getLogger(TicketCreateAMQPPublisher.class);

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public TicketCreateAMQPPublisher(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void createTicketActivity(Guest.GuestUUID guestUUID, TicketingActivity ticketingActivity) {
        log.info("publishing event that new ticket is created in amqp publisher");
        var eventHeader = EventHeader.builder().eventID(UUID.randomUUID()).eventCatalog(EventCatalog.TICKETING_RECEIVED_TICKET).build();
        var eventBody = new TicketingReceivedTicketEvent(guestUUID, ticketingActivity.pit(), ticketingActivity.amount());
        try {
            rabbitTemplate.convertAndSend(RabbitMQTopology.TICKET_EVENTS_FAN_OUT, "ticket.created", EventMessage.builder().eventHeader(eventHeader).eventBody(objectMapper.writeValueAsString(eventBody)).build());
            log.info("succesful");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

package be.kdg.prog6.parkManagement.adapters.in;

import be.kdg.prog6.common.events.EventMessage;
import be.kdg.prog6.parkManagement.adapters.config.RabbitMQTopology;
import be.kdg.prog6.parkManagement.events.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RabbitEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitEventHandler.class);
    private final List<ParkManagementEventHandler<? extends Event>> parkManagementEventHandlers;


    @Autowired
    public RabbitEventHandler(List<ParkManagementEventHandler<? extends Event>> parkManagementEventHandlers) {
        this.parkManagementEventHandlers = parkManagementEventHandlers;
    }

    @RabbitListener(queues = RabbitMQTopology.TICKET_EVENTS_QUEUE, messageConverter = "#{jackson2JsonMessageConverter}")
    public void receiveEventMessage(EventMessage eventMessage) {
        LOGGER.info(eventMessage.toString());
        parkManagementEventHandlers.stream().filter(parkManagementEventHandler -> parkManagementEventHandler.appliesTo(eventMessage.getEventHeader().getEventType()))
                .forEach(parkManagementEventHandler ->
                        parkManagementEventHandler.receive(eventMessage).handle(parkManagementEventHandler.map(eventMessage.getEventBody())));
    }


    @RabbitListener(queues = RabbitMQTopology.ATTRACTION_PARKMANAGEMENT_QUEUE, messageConverter = "#{jackson2JsonMessageConverter}")
    public void receiveEventMessage2(EventMessage eventMessage) {
        LOGGER.info(eventMessage.toString());
        parkManagementEventHandlers.stream().filter(parkManagementEventHandler -> parkManagementEventHandler.appliesTo(eventMessage.getEventHeader().getEventType()))
                .forEach(parkManagementEventHandler ->
                        parkManagementEventHandler.receive(eventMessage).handle(parkManagementEventHandler.map(eventMessage.getEventBody())));
    }


    @RabbitListener(queues = RabbitMQTopology.ADMISSION_EVENTS_QUEUE, messageConverter = "#{jackson2JsonMessageConverter}")
    public void receiveEventMessage3(EventMessage eventMessage) {
        LOGGER.info(eventMessage.toString());
        parkManagementEventHandlers.stream().filter(parkManagementEventHandler -> parkManagementEventHandler.appliesTo(eventMessage.getEventHeader().getEventType()))
                .forEach(parkManagementEventHandler ->
                        parkManagementEventHandler.receive(eventMessage).handle(parkManagementEventHandler.map(eventMessage.getEventBody())));
    }


}

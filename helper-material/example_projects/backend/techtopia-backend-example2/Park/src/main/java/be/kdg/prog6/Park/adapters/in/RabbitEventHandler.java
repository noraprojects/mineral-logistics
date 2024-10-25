package be.kdg.prog6.Park.adapters.in;

import be.kdg.prog6.Park.adapters.config.RabbitMQTopology;
import be.kdg.prog6.Park.events.Event;
import be.kdg.prog6.common.events.EventMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RabbitEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitEventHandler.class);
    private final List<ParkEventHandler<? extends Event>> parkEventHandlers;


    @Autowired
    public RabbitEventHandler(List<ParkEventHandler<? extends Event>> parkEventHandlers) {
        this.parkEventHandlers = parkEventHandlers;
    }

    @RabbitListener(queues = RabbitMQTopology.STAND_EVENTS_QUEUE, messageConverter = "#{jackson2JsonMessageConverter}")
    public void receiveEventMessage(EventMessage eventMessage) {
        LOGGER.info(eventMessage.toString());
        parkEventHandlers.stream().filter(parkEventHandler -> parkEventHandler.appliesTo(eventMessage.getEventHeader().getEventType()))
                .forEach(parkEventHandler ->
                        parkEventHandler.receive(eventMessage).handle(parkEventHandler.map(eventMessage.getEventBody())));
    }


    @RabbitListener(queues = RabbitMQTopology.ATTRACTION_THROUGHPUT_QUEUE, messageConverter = "#{jackson2JsonMessageConverter}")
    public void receiveEventMessageThroughput(EventMessage eventMessage) {
        LOGGER.info(eventMessage.toString());
        parkEventHandlers.stream().filter(parkEventHandler -> parkEventHandler.appliesTo(eventMessage.getEventHeader().getEventType()))
                .forEach(parkEventHandler ->
                        parkEventHandler.receive(eventMessage).handle(parkEventHandler.map(eventMessage.getEventBody())));
    }


}

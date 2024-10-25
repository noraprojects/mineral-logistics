package be.kdg.prog6.grandparents.adapters.in;

import be.kdg.prog6.common.events.EventMessage;
import be.kdg.prog6.grandparents.adapters.config.RabbitMQModuleTopology;
import be.kdg.prog6.grandparents.events.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RabbitEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitEventHandler.class);
    private final List<PiggyBankEventHandler<? extends Event>> piggyBankEventHandlers;


    @Autowired
    public RabbitEventHandler(List<PiggyBankEventHandler<? extends Event>> piggyBankEventHandlers) {
        this.piggyBankEventHandlers = piggyBankEventHandlers;
    }

    @RabbitListener(queues = RabbitMQModuleTopology.PIGGY_BANK_EVENTS_QUEUE, messageConverter = "#{jackson2JsonMessageConverter}")
    public void receiveEventMessage(EventMessage eventMessage) {
        LOGGER.info(eventMessage.toString());
        piggyBankEventHandlers.stream().filter(piggyBankEventHandler -> piggyBankEventHandler.appliesTo(eventMessage.getEventHeader().getEventType()))
                .forEach(piggyBankEventHandler ->
                        piggyBankEventHandler.receive(eventMessage).handle(piggyBankEventHandler.map(eventMessage.getEventBody())));
    }

}

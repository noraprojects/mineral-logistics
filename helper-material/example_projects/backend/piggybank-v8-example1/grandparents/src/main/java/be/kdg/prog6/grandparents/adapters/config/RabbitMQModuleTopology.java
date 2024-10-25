package be.kdg.prog6.grandparents.adapters.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQModuleTopology {


    public static final String PIGGY_BANK_EVENTS_FAN_OUT = "piggybank-events";
    public static final String PIGGY_BANK_EVENTS_QUEUE = "piggybank-grandparents-queue";

    public static final String FAMILY_APPOINTMENT_COMMANDS = "family-appointment-commands";


    @Bean
    FanoutExchange piggyBankEventsExchange() {
        return new FanoutExchange(PIGGY_BANK_EVENTS_FAN_OUT);
    }

    @Bean
    Queue piggybankEventsQueue() {
        return new Queue(PIGGY_BANK_EVENTS_QUEUE);
    }


    @Bean
    Binding bindHelloQueueToTopic(FanoutExchange piggyBankEventsExchange, Queue piggybankEventsQueue) {
        return BindingBuilder.bind(piggybankEventsQueue).to(piggyBankEventsExchange);
    }

    @Bean
    TopicExchange familyAppointmentCommandExchange() {
        return new TopicExchange(FAMILY_APPOINTMENT_COMMANDS);
    }

}

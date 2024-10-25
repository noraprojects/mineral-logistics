package be.kdg.prog6.family.adapters.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQModuleTopology {


    public static final String PIGGY_BANK_EVENTS_FAN_OUT = "piggybank-events";
    public static final String FAMILY_APPOINTMENT_COMMANDS = "family-appointment-commands";
    public static final String PIGGY_BANK_EVENTS_QUEUE = "piggybank-family-queue";
    public static final String FAMILY_APPOINTMENT_QUEUE = "family-appointment-queue";


    @Bean
    FanoutExchange piggyBankEventsExchange() {
        return new FanoutExchange(PIGGY_BANK_EVENTS_FAN_OUT);
    }

    @Bean
    Queue piggybankEventsQueue() {
        return new Queue(PIGGY_BANK_EVENTS_QUEUE);
    }


    @Bean
    Binding eventsBinding(FanoutExchange piggyBankEventsExchange, Queue piggybankEventsQueue) {
        return BindingBuilder.bind(piggybankEventsQueue).to(piggyBankEventsExchange);
    }


    @Bean
    TopicExchange familyAppointmentCommandExchange() {
        return new TopicExchange(FAMILY_APPOINTMENT_COMMANDS);
    }

    @Bean
    Queue familyAppointmentCommandQueue() {
        return new Queue(FAMILY_APPOINTMENT_QUEUE);
    }

    @Bean
    Binding appointmentCommandBinding(TopicExchange familyAppointmentCommandExchange, Queue familyAppointmentCommandQueue) {
        return BindingBuilder.bind(familyAppointmentCommandQueue).to(familyAppointmentCommandExchange).with("appointment.command.#");
    }


}

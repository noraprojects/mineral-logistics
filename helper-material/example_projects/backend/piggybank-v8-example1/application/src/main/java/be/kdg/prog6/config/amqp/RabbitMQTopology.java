package be.kdg.prog6.config.amqp;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQTopology {


    public static final String PIGGY_BANK_EVENTS_FAN_OUT = "piggybank-events";
    public static final String PIGGY_BANK_GPA_EVENTS_QUEUE = "piggybank-grandparents-queue";
    public static final String PIGGY_BANK_FAMILY_EVENTS_QUEUE = "piggybank-family-queue";
    public static final String FAMILY_APPOINTMENT_QUEUE = "family-appointment-queue";

    public static final String FAMILY_APPOINTMENT_COMMANDS = "family-appointment-commands";


    @Bean
    FanoutExchange piggyBankEventsExchange() {
        return new FanoutExchange(PIGGY_BANK_EVENTS_FAN_OUT);
    }

    @Bean
    Queue piggybankGpaEventsQueue() {
        return new Queue(PIGGY_BANK_GPA_EVENTS_QUEUE);
    }


    @Bean
    Binding bindgapQueueToTopic(FanoutExchange piggyBankEventsExchange, Queue piggybankGpaEventsQueue) {
        return BindingBuilder.bind(piggybankGpaEventsQueue).to(piggyBankEventsExchange);
    }


    @Bean
    Queue piggybankFamilyEventsQueue() {
        return new Queue(PIGGY_BANK_FAMILY_EVENTS_QUEUE);
    }


    @Bean
    Binding eventsBinding(FanoutExchange piggyBankEventsExchange, Queue piggybankFamilyEventsQueue) {
        return BindingBuilder.bind(piggybankFamilyEventsQueue).to(piggyBankEventsExchange);
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

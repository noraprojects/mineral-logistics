package be.kdg.prog6.ticketing.adapters.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQTopology {


    public static final String TICKET_EVENTS_FAN_OUT = "ticket-events";
    public static final String TICKET_EVENTS_QUEUE = "ticket-parkmanagement-queue";


    @Bean
    FanoutExchange ticketEventsExchange() {
        return new FanoutExchange(TICKET_EVENTS_FAN_OUT);
    }

    @Bean
    Queue ticketEventsQueue() {
        return new Queue(TICKET_EVENTS_QUEUE);
    }


    @Bean
    Binding eventsBinding(FanoutExchange exchange, Queue queue) {
        return BindingBuilder.bind(queue).to(exchange);
    }


}

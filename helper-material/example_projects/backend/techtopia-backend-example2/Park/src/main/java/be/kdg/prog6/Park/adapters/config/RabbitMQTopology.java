package be.kdg.prog6.Park.adapters.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQTopology {


    public static final String QUEUE_REACHED_EVENTS_FAN_OUT = "Queue-Reached-events";
    public static final String ATTRACTION_PARKMANAGEMENT_QUEUE = "attraction-parkmanagement-queue";
    public static final String ATTRACTION_THROUGHPUT_FAN_OUT = "attraction-throughput-events";
    public static final String ATTRACTION_THROUGHPUT_QUEUE = "attraction-throughput-queue";
    public static final String STAND_EVENTS_FAN_OUT = "stand-events";
    public static final String STAND_EVENTS_QUEUE = "stand-parkmanagement-queue";

    @Bean
    FanoutExchange attractionThroughputExchange() {
        return new FanoutExchange(ATTRACTION_THROUGHPUT_FAN_OUT);
    }

    @Bean
    Queue attractionThroughputQueue() {
        return new Queue(ATTRACTION_THROUGHPUT_QUEUE);
    }


    @Bean
    Binding attractionBinding(FanoutExchange attractionThroughputExchange, Queue attractionThroughputQueue) {
        return BindingBuilder.bind(attractionThroughputQueue).to(attractionThroughputExchange);
    }


    @Bean
    FanoutExchange attractionEventsExchange() {
        return new FanoutExchange(QUEUE_REACHED_EVENTS_FAN_OUT);
    }

    @Bean
    Queue attractionEventsQueue() {
        return new Queue(ATTRACTION_PARKMANAGEMENT_QUEUE);
    }


    @Bean
    Binding eventsBinding(FanoutExchange attractionEventsExchange, Queue attractionEventsQueue) {
        return BindingBuilder.bind(attractionEventsQueue).to(attractionEventsExchange);
    }


    @Bean
    FanoutExchange standEventsExchange() {
        return new FanoutExchange(STAND_EVENTS_FAN_OUT);
    }

    @Bean
    Queue standEventsQueue() {
        return new Queue(STAND_EVENTS_QUEUE);
    }


    @Bean
    Binding eventsBindingStand(FanoutExchange standEventsExchange, Queue standEventsQueue) {
        return BindingBuilder.bind(standEventsQueue).to(standEventsExchange);
    }


}

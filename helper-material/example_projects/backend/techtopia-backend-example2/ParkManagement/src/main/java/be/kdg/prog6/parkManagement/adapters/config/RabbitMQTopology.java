package be.kdg.prog6.parkManagement.adapters.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQTopology {

    public static final String TICKETING_EVENTS_FAN_OUT = "ticket-events";
    public static final String QUEUE_REACHED_EVENTS_FAN_OUT = "Queue-Reached-events";
    public static final String STAND_EVENTS_FAN_OUT = "stand-events";
    public static final String ATTRACTION_THROUGHPUT_FAN_OUT = "attraction-throughput-events";
    public static final String ADMISSION_EVENTS_FAN_OUT = "admission-events";

    public static final String TICKET_EVENTS_QUEUE = "ticket-parkmanagement-queue";
    public static final String ATTRACTION_PARKMANAGEMENT_QUEUE = "attraction-parkmanagement-queue";
    public static final String STAND_EVENTS_QUEUE = "stand-parkmanagement-queue";
    public static final String ATTRACTION_THROUGHPUT_QUEUE = "attraction-throughput-queue";
    public static final String ADMISSION_EVENTS_QUEUE = "admission-parkmanagement-queue";

    @Bean
    Binding bindReachedQueueToTopic(FanoutExchange attractionEventsExchange, Queue attractionEventsQueue) {
        return BindingBuilder.bind(attractionEventsQueue).to(attractionEventsExchange);
    }

    @Bean
    Binding attractionBinding(FanoutExchange attractionThroughputExchange, Queue attractionThroughputQueue) {
        return BindingBuilder.bind(attractionThroughputQueue).to(attractionThroughputExchange);
    }

    @Bean
    Binding bindToStandQueue(FanoutExchange standEventsExchange, Queue standEventsQueue) {
        return BindingBuilder.bind(standEventsQueue).to(standEventsExchange);
    }

    @Bean
    Binding bindToAdmissionQueue(FanoutExchange admissionEventsExchange, Queue admissionEventsQueue) {
        return BindingBuilder.bind(admissionEventsQueue).to(admissionEventsExchange);
    }

    @Bean
    Binding bindHelloQueueToTopic(FanoutExchange ticketingEventsExchange, Queue ticketingEventsQueue) {
        return BindingBuilder.bind(ticketingEventsQueue).to(ticketingEventsExchange);
    }

    @Bean
    FanoutExchange admissionEventsExchange() {
        return new FanoutExchange(ADMISSION_EVENTS_FAN_OUT);
    }

    @Bean
    FanoutExchange ticketingEventsExchange() {
        return new FanoutExchange(TICKETING_EVENTS_FAN_OUT);
    }

    @Bean
    FanoutExchange attractionEventsExchange() {
        return new FanoutExchange(QUEUE_REACHED_EVENTS_FAN_OUT);
    }

    @Bean
    FanoutExchange standEventsExchange() {
        return new FanoutExchange(STAND_EVENTS_FAN_OUT);
    }

    @Bean
    FanoutExchange attractionThroughputExchange() {
        return new FanoutExchange(ATTRACTION_THROUGHPUT_FAN_OUT);
    }


    @Bean
    Queue admissionEventsQueue() {
        return new Queue(ADMISSION_EVENTS_QUEUE);
    }

    @Bean
    Queue ticketingEventsQueue() {
        return new Queue(TICKET_EVENTS_QUEUE);
    }


    @Bean
    Queue attractionEventsQueue() {
        return new Queue(ATTRACTION_PARKMANAGEMENT_QUEUE);
    }

    @Bean
    Queue standEventsQueue() {
        return new Queue(STAND_EVENTS_QUEUE);
    }

    @Bean
    Queue attractionThroughputQueue() {
        return new Queue(ATTRACTION_THROUGHPUT_QUEUE);
    }


}
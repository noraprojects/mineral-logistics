package be.kdg.prog6.Admission.adapters.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQTopology {


    public static final String ADMISSION_EVENTS_FAN_OUT = "admission-events";
    public static final String ADMISSION_EVENTS_QUEUE = "admission-parkmanagement-queue";


    @Bean
    FanoutExchange admissionEventsExchange() {
        return new FanoutExchange(ADMISSION_EVENTS_FAN_OUT);
    }

    @Bean
    Queue admissionEventsQueue() {
        return new Queue(ADMISSION_EVENTS_QUEUE);
    }

    @Bean
    Binding bindHelloQueueToTopic(FanoutExchange fanoutExchange, Queue queue) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    @Bean
    Binding bindReachedQueueToTopic(FanoutExchange fanoutExchange, Queue queue) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }


}

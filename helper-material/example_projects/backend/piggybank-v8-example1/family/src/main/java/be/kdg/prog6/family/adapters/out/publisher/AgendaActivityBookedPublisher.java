package be.kdg.prog6.family.adapters.out.publisher;

import be.kdg.prog6.family.adapters.out.amqp.PiggyBankCreateAMQPPublisher;
import be.kdg.prog6.family.domain.AgendaActivity;
import be.kdg.prog6.family.events.AgendaActivityCreated;
import be.kdg.prog6.family.ports.out.AgendaActivityBookedPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class AgendaActivityBookedPublisher implements AgendaActivityBookedPort {

    public static final Logger log = LoggerFactory.getLogger(PiggyBankCreateAMQPPublisher.class);


    private final ApplicationEventPublisher applicationEventPublisher;


    public AgendaActivityBookedPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }


    @Override
    public void agendaActivityBooked(AgendaActivity activity) {
        applicationEventPublisher.publishEvent(new AgendaActivityCreated(activity.personUUID(), activity.date()));
    }
}

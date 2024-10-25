package be.kdg.prog6.family.adapters.in.amqp;

import be.kdg.prog6.common.facades.CreateAppointmentCommand;
import be.kdg.prog6.family.adapters.config.RabbitMQModuleTopology;
import be.kdg.prog6.family.ports.in.AppointmentForShoppingUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AppointmentInAmqpAdapter {

    public static final Logger log = LoggerFactory.getLogger(AppointmentInAmqpAdapter.class);

    private final AppointmentForShoppingUseCase appointmentForShoppingUseCase;

    public AppointmentInAmqpAdapter(AppointmentForShoppingUseCase appointmentForShoppingUseCase) {
        this.appointmentForShoppingUseCase = appointmentForShoppingUseCase;
    }

    @RabbitListener(queues = RabbitMQModuleTopology.FAMILY_APPOINTMENT_QUEUE, messageConverter = "#{jackson2JsonMessageConverter}")
    /** if you make this method give a returnType then you'll have to post a message with a replyToHeader
     * Or you can wait on the event... most of the time reply is the safe option... otherwise you could have distributed loops
     * event -> command -> event -> command -> ... (let's say that we decide to donate an extra 50 when an appointment has been made for instance...
     * For science we throw an event that an agenda activity is created
     */
    public void makeAppointment(CreateAppointmentCommand command) {
        appointmentForShoppingUseCase.bookAnAppointmentForDate(command);
    }
}

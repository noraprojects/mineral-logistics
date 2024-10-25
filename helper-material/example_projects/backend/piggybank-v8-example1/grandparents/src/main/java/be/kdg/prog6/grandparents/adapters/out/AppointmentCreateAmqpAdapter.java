package be.kdg.prog6.grandparents.adapters.out;

import be.kdg.prog6.common.facades.CreateAppointmentCommand;
import be.kdg.prog6.grandparents.adapters.config.RabbitMQModuleTopology;
import be.kdg.prog6.grandparents.domain.Appointment;
import be.kdg.prog6.grandparents.ports.out.AppointmentCreatePort;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class AppointmentCreateAmqpAdapter implements AppointmentCreatePort {

    private final RabbitTemplate rabbitTemplate;

    public AppointmentCreateAmqpAdapter(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void appointmentStateChanged(Appointment appointment) {
        rabbitTemplate.convertAndSend(RabbitMQModuleTopology.FAMILY_APPOINTMENT_COMMANDS, "appointment.command.create", new CreateAppointmentCommand(appointment.getPersonUUID().uuid(), appointment.getDate()));
    }
}

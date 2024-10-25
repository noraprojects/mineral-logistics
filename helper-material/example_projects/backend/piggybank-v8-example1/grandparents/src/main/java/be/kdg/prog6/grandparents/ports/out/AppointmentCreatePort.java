package be.kdg.prog6.grandparents.ports.out;

import be.kdg.prog6.grandparents.domain.Appointment;

public interface AppointmentCreatePort {

    void appointmentStateChanged(Appointment appointment);

}

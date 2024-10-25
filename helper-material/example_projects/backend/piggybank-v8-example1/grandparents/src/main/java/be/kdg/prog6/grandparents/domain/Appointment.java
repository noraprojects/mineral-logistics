package be.kdg.prog6.grandparents.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Appointment {

    private final AppointmentUUID appointmentUUID = new AppointmentUUID(UUID.randomUUID());

    private LocalDate date;
    private PiggyBank.PersonUUID personUUID;


    public record AppointmentUUID(UUID uuid) {

    }

    public static Appointment forToday(PiggyBank.PersonUUID personUUID) {
        Appointment appointment = new Appointment();
        appointment.date = LocalDate.now();
        appointment.personUUID = personUUID;
        return appointment;
    }

    public AppointmentUUID getAppointmentUUID() {
        return appointmentUUID;
    }

    public PiggyBank.PersonUUID getPersonUUID() {
        return personUUID;
    }

    public LocalDate getDate() {
        return date;
    }
}

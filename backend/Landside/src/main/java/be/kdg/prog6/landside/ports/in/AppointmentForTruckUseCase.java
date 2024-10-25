package be.kdg.prog6.landside.ports.in;

import be.kdg.prog6.common.events.facades.CreateAppointmentCommand;

public interface AppointmentForTruckUseCase {
    boolean bookAnAppointmentForDate(CreateAppointmentCommand createAppointmentCommand);
}

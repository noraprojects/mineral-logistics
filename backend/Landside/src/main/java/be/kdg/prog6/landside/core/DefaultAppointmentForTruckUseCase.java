package be.kdg.prog6.landside.core;

import be.kdg.prog6.common.events.facades.CreateAppointmentCommand;
import be.kdg.prog6.landside.ports.in.AppointmentForTruckUseCase;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DefaultAppointmentForTruckUseCase implements AppointmentForTruckUseCase {

    @Override
    @Transactional
    public boolean bookAnAppointmentForDate(CreateAppointmentCommand createAppointmentCommand) {
        return false;
    }
}

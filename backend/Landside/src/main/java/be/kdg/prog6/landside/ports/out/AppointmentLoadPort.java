package be.kdg.prog6.landside.ports.out;


import be.kdg.prog6.landside.adapters.out.AppointmentJpaEntity;
import be.kdg.prog6.landside.domain.LicensePlate;

import java.util.List;

public interface AppointmentLoadPort {
    List<AppointmentJpaEntity> loadAppointmentsByTruck(LicensePlate.LicensePlateUUID licensePlateUUID);
}


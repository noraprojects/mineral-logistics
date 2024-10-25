package be.kdg.prog6.landside.adapters.out.db;

import be.kdg.prog6.landside.adapters.out.AppointmentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<AppointmentJpaEntity, UUID> {
    List<AppointmentJpaEntity> findAllByLicense(UUID uuid);
}

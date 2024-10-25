package be.kdg.prog6.parkManagement.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


public interface ParkActivityRepository extends JpaRepository<ParkJpaActivity, UUID> {
}

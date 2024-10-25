package be.kdg.prog6.parkManagement.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RefreshmentStandActivityRepository extends JpaRepository<RefreshmentStandJpaActivity, UUID> {
}

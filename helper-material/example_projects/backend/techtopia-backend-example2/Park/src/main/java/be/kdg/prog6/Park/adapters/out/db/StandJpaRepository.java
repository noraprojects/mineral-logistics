package be.kdg.prog6.Park.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StandJpaRepository extends JpaRepository<StandJpaEntity, UUID> {
}
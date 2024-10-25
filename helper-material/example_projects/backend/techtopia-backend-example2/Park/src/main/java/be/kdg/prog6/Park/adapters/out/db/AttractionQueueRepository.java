package be.kdg.prog6.Park.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AttractionQueueRepository extends JpaRepository<AttractionJpaEntity, UUID> {


    Optional<AttractionJpaEntity> findByUuid(UUID uuid);
}

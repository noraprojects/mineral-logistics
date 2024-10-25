package be.kdg.prog6.Park.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface AttractionQueueActivityRepository extends JpaRepository<AttractionQueueJpaActivity, UUID> {

    List<AttractionQueueJpaActivity> findByattraction(UUID attractionUUID);




}

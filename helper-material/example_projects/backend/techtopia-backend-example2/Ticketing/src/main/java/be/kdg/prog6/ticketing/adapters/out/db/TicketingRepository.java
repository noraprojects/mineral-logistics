package be.kdg.prog6.ticketing.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TicketingRepository extends JpaRepository<TicketingJpaEntity, UUID> {
    List<TicketingJpaEntity> findAllByOwner(UUID uuid);
}

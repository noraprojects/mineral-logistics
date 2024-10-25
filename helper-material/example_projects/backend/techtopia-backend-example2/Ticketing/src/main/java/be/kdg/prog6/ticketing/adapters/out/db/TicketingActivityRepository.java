package be.kdg.prog6.ticketing.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketingActivityRepository extends JpaRepository<TicketingJpaActivity, UUID> {
}

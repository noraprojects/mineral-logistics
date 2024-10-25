package be.kdg.prog6.grandparents.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PiggyBankProjectionsRepository extends JpaRepository<PiggyBankProjectionJpaEntity, Long> {

    Optional<PiggyBankProjectionJpaEntity> findByPiggyBankUUID(UUID piggyBankUUID);
}

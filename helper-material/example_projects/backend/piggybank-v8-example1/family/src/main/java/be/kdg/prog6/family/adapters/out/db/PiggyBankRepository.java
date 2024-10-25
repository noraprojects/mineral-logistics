package be.kdg.prog6.family.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PiggyBankRepository extends JpaRepository<PiggyBankJpaEntity, UUID> {

    Optional<PiggyBankJpaEntity> findByOwner(UUID uuid);
}

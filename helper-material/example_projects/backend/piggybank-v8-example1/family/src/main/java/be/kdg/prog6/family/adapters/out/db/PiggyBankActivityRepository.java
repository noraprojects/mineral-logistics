package be.kdg.prog6.family.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PiggyBankActivityRepository extends JpaRepository<PiggyBankJpaActivity, UUID> {

    List<PiggyBankJpaActivity> findByPiggyBank(UUID piggyBankUUID);

    List<PiggyBankJpaActivity> findByPiggyBankAndPitBetween(UUID piggyBankUUID, LocalDateTime start, LocalDateTime end);

    List<PiggyBankJpaActivity> findByPiggyBankAndPitGreaterThan(UUID piggyBankUUID, LocalDateTime from);


}

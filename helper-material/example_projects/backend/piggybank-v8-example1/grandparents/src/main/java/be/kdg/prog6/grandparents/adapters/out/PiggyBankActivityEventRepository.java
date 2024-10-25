package be.kdg.prog6.grandparents.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PiggyBankActivityEventRepository extends JpaRepository<PiggyBankActivityJpaEntity, Long> {
}

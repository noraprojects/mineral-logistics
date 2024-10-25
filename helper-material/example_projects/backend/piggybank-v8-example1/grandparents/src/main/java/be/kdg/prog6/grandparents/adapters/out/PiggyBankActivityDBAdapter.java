package be.kdg.prog6.grandparents.adapters.out;

import be.kdg.prog6.grandparents.domain.PiggyBankActivityEvent;
import be.kdg.prog6.grandparents.ports.out.PiggyBankActivityPort;
import org.springframework.stereotype.Repository;

@Repository
public class PiggyBankActivityDBAdapter implements PiggyBankActivityPort {

    private final PiggyBankActivityEventRepository piggyBankActivityEventRepository;

    public PiggyBankActivityDBAdapter(PiggyBankActivityEventRepository piggyBankActivityEventRepository) {
        this.piggyBankActivityEventRepository = piggyBankActivityEventRepository;
    }

    @Override
    public void saveState(PiggyBankActivityEvent piggyBankActivityEvent) {
        PiggyBankActivityJpaEntity piggyBankActivityJpaEntity = new PiggyBankActivityJpaEntity();
        piggyBankActivityJpaEntity.setAction(piggyBankActivityEvent.action());
        piggyBankActivityJpaEntity.setPit(piggyBankActivityEvent.pit());
        piggyBankActivityJpaEntity.setAmount(piggyBankActivityEvent.amount());
        piggyBankActivityEventRepository.save(piggyBankActivityJpaEntity);
    }
}

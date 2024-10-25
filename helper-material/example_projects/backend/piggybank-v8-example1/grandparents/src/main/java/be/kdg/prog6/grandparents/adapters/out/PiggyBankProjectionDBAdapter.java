package be.kdg.prog6.grandparents.adapters.out;

import be.kdg.prog6.grandparents.domain.PiggyBank;
import be.kdg.prog6.grandparents.ports.out.PiggyBankProjectionPort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class PiggyBankProjectionDBAdapter implements PiggyBankProjectionPort {

    private final PiggyBankProjectionsRepository piggyBankProjectionsRepository;

    public PiggyBankProjectionDBAdapter(PiggyBankProjectionsRepository piggyBankProjectionsRepository) {
        this.piggyBankProjectionsRepository = piggyBankProjectionsRepository;
    }

    @Override
    public Optional<PiggyBank> loadPiggyBank(UUID piggyBankUUID) {
        Optional<PiggyBankProjectionJpaEntity> piggyBankJpaEntity = piggyBankProjectionsRepository.findByPiggyBankUUID(piggyBankUUID);
        if (piggyBankJpaEntity.isPresent()) {
            PiggyBank piggyBank = new PiggyBank(new PiggyBank.PiggyBankUUID(piggyBankUUID), new PiggyBank.PersonUUID(piggyBankJpaEntity.get().getOwnerUUID()));
            piggyBank.setCurrentBalance(piggyBankJpaEntity.get().getCurrentBalance());
            return Optional.of(piggyBank);
        }
        return Optional.empty();

    }

    @Override
    public void savePiggybank(PiggyBank piggyBank) {
        Optional<PiggyBankProjectionJpaEntity> piggyBankJpaEntity = piggyBankProjectionsRepository.findByPiggyBankUUID(piggyBank.getPiggyBankUUID().uuid());
        if (piggyBankJpaEntity.isPresent()) {
            PiggyBankProjectionJpaEntity toUpdate = piggyBankJpaEntity.get();
            toUpdate.setCurrentBalance(piggyBank.getCurrentBalance());
            piggyBankProjectionsRepository.save(toUpdate);
        } else {
            PiggyBankProjectionJpaEntity toCreate = new PiggyBankProjectionJpaEntity();
            toCreate.setPiggyBankUUID(piggyBank.getPiggyBankUUID().uuid());
            toCreate.setOwnerUUID(piggyBank.getPersonUUID().uuid());
            toCreate.setCurrentBalance(piggyBank.getCurrentBalance());
            piggyBankProjectionsRepository.save(toCreate);
        }

    }
}

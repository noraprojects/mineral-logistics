package be.kdg.prog6.family.adapters.out.db;

import be.kdg.prog6.family.domain.ActivityWindow;
import be.kdg.prog6.family.domain.Person;
import be.kdg.prog6.family.domain.PiggyBank;
import be.kdg.prog6.family.domain.PiggyBankActivity;
import be.kdg.prog6.family.events.AgendaActivityCreated;
import be.kdg.prog6.family.ports.out.PiggyBankActivityCreatePort;
import be.kdg.prog6.family.ports.out.PiggyBankActivityLoadPort;
import be.kdg.prog6.family.ports.out.PiggyBankLoadPort;
import be.kdg.prog6.family.ports.out.PiggyBankUpdatePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public class PiggyBankDBAdapter implements PiggyBankUpdatePort, PiggyBankLoadPort, PiggyBankActivityLoadPort, PiggyBankActivityCreatePort {

    private static final Logger LOGGER = LoggerFactory.getLogger(PiggyBankDBAdapter.class);

    private final PiggyBankRepository piggyBankRepository;
    private final PiggyBankActivityRepository piggyBankActivityRepository;

    public PiggyBankDBAdapter(PiggyBankRepository piggyBankRepository, PiggyBankActivityRepository piggyBankActivityRepository) {
        this.piggyBankRepository = piggyBankRepository;
        this.piggyBankActivityRepository = piggyBankActivityRepository;
    }

    @Override
    public void updatePiggyBank(PiggyBank piggyBank) {
        PiggyBankJpaEntity piggyBankJpaEntity = new PiggyBankJpaEntity(piggyBank.getPiggyBankUUID().uuid());
        piggyBankJpaEntity.setOwner(piggyBank.getOwner().uuid());
        piggyBankJpaEntity.setBaseBalance(piggyBank.getBaseBalance());
        piggyBankJpaEntity.setBaseBalanceDate(piggyBank.getBaseBalanceDate());
        piggyBankRepository.save(piggyBankJpaEntity);
    }


    @Override
    public Optional<PiggyBank> loadPiggyBankForOwner(Person.PersonUUID ownerUuid) {
        Optional<PiggyBankJpaEntity> piggyBankJpaEntity = piggyBankRepository.findByOwner(ownerUuid.uuid());
        if (piggyBankJpaEntity.isEmpty()) {
            return Optional.empty();
        }
        PiggyBank piggyBank = new PiggyBank(new PiggyBank.PiggyBankUUID(piggyBankJpaEntity.get().getUuid()), new Person.PersonUUID(piggyBankJpaEntity.get().getOwner()), new ActivityWindow(), piggyBankJpaEntity.get().getBaseBalance(), piggyBankJpaEntity.get().getBaseBalanceDate());

        List<PiggyBankJpaActivity> piggyBankJpaActivityList = null;
        if (piggyBankJpaEntity.get().getBaseBalanceDate() != null) {
            piggyBankJpaActivityList = piggyBankActivityRepository.findByPiggyBankAndPitGreaterThan(piggyBank.getPiggyBankUUID().uuid(), piggyBankJpaEntity.get().getBaseBalanceDate());
        } else {
            piggyBankJpaActivityList = piggyBankActivityRepository.findByPiggyBank(piggyBank.getPiggyBankUUID().uuid());
        }

        for (PiggyBankJpaActivity piggyBankActivity : piggyBankJpaActivityList) {
            piggyBank.addPiggyBankActivity(new PiggyBankActivity(piggyBankActivity.getPiggyBankAction(), piggyBankActivity.getAmount(), piggyBankActivity.getPit()));
        }
        return Optional.of(piggyBank);
    }

    @Override
    public void createPiggyBankActivity(PiggyBank.PiggyBankUUID piggyBankUUID, PiggyBankActivity piggyBankActivity) {
        PiggyBankJpaActivity piggyBankJpaActivity = new PiggyBankJpaActivity();
        piggyBankJpaActivity.setAmount(piggyBankActivity.amount());
        piggyBankJpaActivity.setPiggyBankAction(piggyBankActivity.action());
        piggyBankJpaActivity.setPiggyBank(piggyBankUUID.uuid());
        piggyBankJpaActivity.setPit(piggyBankActivity.pit());
        piggyBankActivityRepository.save(piggyBankJpaActivity);
    }

    @Override
    public Optional<PiggyBank> loadPiggyBank(UUID ownerUUID, LocalDateTime start, LocalDateTime end) {
        Optional<PiggyBankJpaEntity> piggyBankJpaEntity = piggyBankRepository.findByOwner(ownerUUID);
        if (piggyBankJpaEntity.isEmpty()) return Optional.empty();

        PiggyBank piggyBank = new PiggyBank(new PiggyBank.PiggyBankUUID(piggyBankJpaEntity.get().getUuid()), new Person.PersonUUID(piggyBankJpaEntity.get().getOwner()), new ActivityWindow());

        List<PiggyBankJpaActivity> piggyBankJpaActivityList = piggyBankActivityRepository.findByPiggyBankAndPitBetween(piggyBank.getPiggyBankUUID().uuid(), start, end);
        for (PiggyBankJpaActivity piggyBankActivity : piggyBankJpaActivityList) {
            piggyBank.addPiggyBankActivity(new PiggyBankActivity(piggyBankActivity.getPiggyBankAction(), piggyBankActivity.getAmount(), piggyBankActivity.getPit()));
        }
        return Optional.of(piggyBank);
    }

    @EventListener
    //This is a technical consideration (performance) -> so no need to go through all layers (no real USE CASE impacted)
    public void createSnapshot(AgendaActivityCreated event) {
        System.out.println(">>>>>>> CREATING SNAPSHOT!!!!");
        Optional<PiggyBank> optionalPiggyBank = this.loadPiggyBankForOwner(new Person.PersonUUID(event.personUUID()));
        if (optionalPiggyBank.isPresent()) {
            PiggyBank piggyBank = optionalPiggyBank.get();
            piggyBank.snapshotBalance();
            this.updatePiggyBank(piggyBank);
        }
    }
}

package be.kdg.prog6.family.core;

import be.kdg.prog6.family.domain.*;
import be.kdg.prog6.family.ports.in.GiveMoneyCommand;
import be.kdg.prog6.family.ports.in.ReceivingMoneyUseCase;
import be.kdg.prog6.family.ports.out.PiggyBankActivityCreatePort;
import be.kdg.prog6.family.ports.out.PiggyBankCreatePort;
import be.kdg.prog6.family.ports.out.PiggyBankLoadPort;
import be.kdg.prog6.family.ports.out.PiggyBankUpdatePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultReceivingMoneyUseCase implements ReceivingMoneyUseCase {


    private static final Person.PersonUUID BOBBY_ID = new Person.PersonUUID(UUID.fromString("ef01c728-ce36-46b5-a110-84f53fdd9668"));

    private final PiggyBankUpdatePort piggyBankUpdatePort;
    private final PiggyBankCreatePort piggyBankCreatePort;
    private final PiggyBankLoadPort piggyBankLoadPort;
    private final List<PiggyBankActivityCreatePort> piggyBankActivityCreatePorts;


    public DefaultReceivingMoneyUseCase(PiggyBankUpdatePort piggyBankUpdatePort, PiggyBankCreatePort piggyBankCreatePort, PiggyBankLoadPort piggyBankLoadPort, List<PiggyBankActivityCreatePort> piggyBankActivityCreatePorts) {
        this.piggyBankUpdatePort = piggyBankUpdatePort;
        this.piggyBankCreatePort = piggyBankCreatePort;
        this.piggyBankLoadPort = piggyBankLoadPort;
        this.piggyBankActivityCreatePorts = piggyBankActivityCreatePorts;
    }

    @Override
    @Transactional
    public void receiveMoney(GiveMoneyCommand giveMoneyCommand) {
        //Weird for now, next week will clean this up
        FamilyMember bobby = new FamilyMember(BOBBY_ID, "Bobby");

        if (giveMoneyCommand.personUUID().equals(bobby.getPersonUUID())) {
            Optional<PiggyBank> optionalPiggyBank = piggyBankLoadPort.loadPiggyBankForOwner(giveMoneyCommand.personUUID());
            PiggyBank piggyBank;
            if (optionalPiggyBank.isEmpty()) {
                    piggyBank = new PiggyBank(new PiggyBank.PiggyBankUUID(UUID.randomUUID()), bobby.getPersonUUID(), new ActivityWindow());
                    piggyBankCreatePort.piggyBankCreated("Bobby", piggyBank);
            } else {
                piggyBank = optionalPiggyBank.get();
            }
            piggyBankUpdatePort.updatePiggyBank(piggyBank);
            PiggyBankActivity activity = piggyBank.putMoney(giveMoneyCommand.amount());
            piggyBankActivityCreatePorts.forEach(port -> port.createPiggyBankActivity(piggyBank.getPiggyBankUUID(), activity));
        }
    }


}

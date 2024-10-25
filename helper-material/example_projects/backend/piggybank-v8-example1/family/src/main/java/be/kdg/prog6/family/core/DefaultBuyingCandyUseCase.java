package be.kdg.prog6.family.core;

import be.kdg.prog6.family.domain.FamilyMember;
import be.kdg.prog6.family.domain.Person;
import be.kdg.prog6.family.domain.PiggyBank;
import be.kdg.prog6.family.domain.PiggyBankActivity;
import be.kdg.prog6.family.ports.in.BuyCandyAmountCommand;
import be.kdg.prog6.family.ports.in.BuyingCandyUseCase;
import be.kdg.prog6.family.ports.out.PiggyBankActivityCreatePort;
import be.kdg.prog6.family.ports.out.PiggyBankLoadPort;
import be.kdg.prog6.family.ports.out.PiggyBankUpdatePort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultBuyingCandyUseCase implements BuyingCandyUseCase {

    private static final Person.PersonUUID BOBBY_ID = new Person.PersonUUID(UUID.fromString("ef01c728-ce36-46b5-a110-84f53fdd9668"));

    private final PiggyBankUpdatePort piggyBankUpdatePort;
    private final PiggyBankLoadPort piggyBankLoadPort;
    private final List<PiggyBankActivityCreatePort> piggyBankActivityCreatePorts;


    public DefaultBuyingCandyUseCase(PiggyBankUpdatePort piggyBankUpdatePort, PiggyBankLoadPort piggyBankLoadPort, List<PiggyBankActivityCreatePort> piggyBankActivityCreatePorts) {
        this.piggyBankUpdatePort = piggyBankUpdatePort;
        this.piggyBankLoadPort = piggyBankLoadPort;
        this.piggyBankActivityCreatePorts = piggyBankActivityCreatePorts;
    }

    @Override
    @Transactional
    public void buyingCandy(BuyCandyAmountCommand buyCandyAmountCommand) {
        //Weird for now, bobby needs to be loaded from the database
        FamilyMember bobby = getBobby();

        if (buyCandyAmountCommand.buyer().equals(bobby.getPersonUUID())) {
            Optional<PiggyBank> optionalPiggyBank = piggyBankLoadPort.loadPiggyBankForOwner(buyCandyAmountCommand.buyer());
            PiggyBank piggyBank;
            if (optionalPiggyBank.isEmpty()) {
                throw new RuntimeException("Blah");
            } else {
                piggyBank = optionalPiggyBank.get();
            }
            Optional<PiggyBankActivity> activity = piggyBank.takeMoney(buyCandyAmountCommand.money());
            piggyBankUpdatePort.updatePiggyBank(piggyBank);
            activity.ifPresent(piggyBankActivity -> piggyBankActivityCreatePorts.forEach(port -> port.createPiggyBankActivity(piggyBank.getPiggyBankUUID(), piggyBankActivity)));
        }
    }

    FamilyMember getBobby() {
        return new FamilyMember(BOBBY_ID, "Bobby");
    }
}

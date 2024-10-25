package be.kdg.prog6.family.core;

import be.kdg.prog6.family.domain.*;
import be.kdg.prog6.family.ports.in.BuyCandyAmountCommand;
import be.kdg.prog6.family.ports.in.BuyingCandyUseCase;
import be.kdg.prog6.family.ports.out.PiggyBankActivityCreatePort;
import be.kdg.prog6.family.ports.out.PiggyBankLoadPort;
import be.kdg.prog6.family.ports.out.PiggyBankUpdatePort;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StubbingDefaultBuyingCandyUseCaseTest {


    @Test
    void buyingCandyViaStubs() {
        AtomicInteger a = new AtomicInteger();
        //You can create stubbed classes, or do inline using lambda's
        BuyingCandyUseCase buyingCandyUseCase = new DefaultBuyingCandyUseCase(
                piggyBank -> {
                },
                ownerUuid -> {
                    return Optional.empty();
                },
                List.of((piggyBankUUID, piggyBankActivity) -> {
                    a.getAndIncrement();
                }));
        buyingCandyUseCase.buyingCandy(new BuyCandyAmountCommand(BigDecimal.valueOf(20), new Person.PersonUUID(UUID.randomUUID())));
        assertEquals(a.intValue(), 0);
    }

    /**
     * The bigger problem here is that we have a smell in the code: getBobby().. we cannot really get rid of it, otherwise we make a mess.
     * The better option is to Stub it or decorate it.
     * And test the decorator
     */

    @Test
    void buyingCandyViaDecoratedStub() {
        AtomicInteger a = new AtomicInteger();
        ActivityWindow testWindow = new ActivityWindow();
        testWindow.add(new PiggyBankActivity(PiggyBankAction.PUT_IN, BigDecimal.valueOf(50), LocalDateTime.now()));
        //You can create stubbed classes, or do inline using lambda's
        BuyingCandyUseCase buyingCandyUseCase = new StubDefaultBuyingCandyUseCase(
                piggyBank -> {
                },
                ownerUuid -> Optional.of(new PiggyBank(new PiggyBank.PiggyBankUUID(UUID.randomUUID()), StubDefaultBuyingCandyUseCase.BOBBY_UUID, testWindow)),
                List.of((piggyBankUUID, piggyBankActivity) -> {
                    a.getAndIncrement();
                }));
        buyingCandyUseCase.buyingCandy(new BuyCandyAmountCommand(BigDecimal.valueOf(20), StubDefaultBuyingCandyUseCase.BOBBY_UUID));
        assertEquals(1, a.intValue());
        assertEquals(BigDecimal.valueOf(30), testWindow.calculateBalance());
    }


    /**
     * This stub decouples the hardcoded Bobby UUID so that we can have full control, without hardcoding the BOBBY ID in our test.
     * This is a pretty good tactic for legacy code, where mocking is hard.
     * Only downside is, that the getBobby is not set to private here, if so, this is something we'll not to change.
     * Making your code testable can for instance make good considerations if something needs to private, package private or protected for isntance.
     */
    static class StubDefaultBuyingCandyUseCase extends DefaultBuyingCandyUseCase {

        private static final Person.PersonUUID BOBBY_UUID = new Person.PersonUUID(UUID.randomUUID());

        public StubDefaultBuyingCandyUseCase(PiggyBankUpdatePort piggyBankUpdatePort, PiggyBankLoadPort piggyBankLoadPort, List<PiggyBankActivityCreatePort> piggyBankActivityCreatePorts) {
            super(piggyBankUpdatePort, piggyBankLoadPort, piggyBankActivityCreatePorts);
        }

        @Override
        FamilyMember getBobby() {
            return new FamilyMember(BOBBY_UUID, "Bobby");
        }
    }


}
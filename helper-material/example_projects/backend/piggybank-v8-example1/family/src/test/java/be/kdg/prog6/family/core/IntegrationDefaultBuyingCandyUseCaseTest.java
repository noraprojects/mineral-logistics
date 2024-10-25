package be.kdg.prog6.family.core;

import be.kdg.prog6.family.domain.*;
import be.kdg.prog6.family.ports.in.BuyCandyAmountCommand;
import be.kdg.prog6.family.ports.in.BuyingCandyUseCase;
import be.kdg.prog6.family.ports.out.PiggyBankActivityCreatePort;
import be.kdg.prog6.family.ports.out.PiggyBankLoadPort;
import be.kdg.prog6.family.ports.out.PiggyBankUpdatePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Import(TestConfigForThisTest.class)
public class IntegrationDefaultBuyingCandyUseCaseTest {


    private static final Person.PersonUUID BOBBY_ID = new Person.PersonUUID(UUID.fromString("ef01c728-ce36-46b5-a110-84f53fdd9668"));


    @Autowired
    private PiggyBankUpdatePort piggyBankUpdatePortMock;
    @Autowired
    @Spy
    private PiggyBankLoadPort piggyBankLoadPortMock;
    @Autowired
    private PiggyBankActivityCreatePort piggyBankActivityCreatePortMock;

    @Captor
    private ArgumentCaptor<PiggyBank> piggyBankArgumentCaptor;


    @Test
    void buyingCandyViaMockUsingCaptor() {

        ActivityWindow testWindow = Mockito.spy(new ActivityWindow());
        testWindow.add(new PiggyBankActivity(PiggyBankAction.PUT_IN, BigDecimal.valueOf(50), LocalDateTime.now()));

        PiggyBank bobbiesPiggyBank = new PiggyBank(new PiggyBank.PiggyBankUUID(UUID.randomUUID()), BOBBY_ID, testWindow);

        when(piggyBankLoadPortMock.loadPiggyBankForOwner(Mockito.any(Person.PersonUUID.class))).thenReturn(Optional.of(bobbiesPiggyBank));

        BuyingCandyUseCase buyingCandyUseCase = new DefaultBuyingCandyUseCase(piggyBankUpdatePortMock, piggyBankLoadPortMock, List.of(piggyBankActivityCreatePortMock));
        buyingCandyUseCase.buyingCandy(new BuyCandyAmountCommand(BigDecimal.valueOf(20), BOBBY_ID));

        verify(piggyBankUpdatePortMock).updatePiggyBank(piggyBankArgumentCaptor.capture());

        assertEquals(BigDecimal.valueOf(30), testWindow.calculateBalance());
        assertEquals(BigDecimal.valueOf(30), piggyBankArgumentCaptor.getValue().getBalance());

        verify(testWindow, times(2)).add(any(PiggyBankActivity.class));
        verify(piggyBankLoadPortMock, times(1)).loadPiggyBankForOwner(any(Person.PersonUUID.class));
    }


}

/**
 * Have more control on how things get injected... this indeed can again be mocks or stubs.
 * But is an integration test as it spins up a spring context.
 */
@TestConfiguration
class TestConfigForThisTest {

    @Bean
    PiggyBankUpdatePort piggyBankUpdatePortMock() {
        return Mockito.mock(PiggyBankUpdatePort.class);
    }

    @Bean
    PiggyBankLoadPort piggyBankLoadPortMock() {
        return Mockito.mock(PiggyBankLoadPort.class);
    }

    @Bean
    PiggyBankActivityCreatePort piggyBankActivityCreatePortMock() {
        return Mockito.mock(PiggyBankActivityCreatePort.class);
    }

}

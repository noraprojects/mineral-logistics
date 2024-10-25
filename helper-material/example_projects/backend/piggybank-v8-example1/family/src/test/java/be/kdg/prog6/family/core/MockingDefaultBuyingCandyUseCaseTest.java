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
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MockingDefaultBuyingCandyUseCaseTest {

    private static final Person.PersonUUID BOBBY_ID = new Person.PersonUUID(UUID.fromString("ef01c728-ce36-46b5-a110-84f53fdd9668"));


    @Mock
    PiggyBankUpdatePort piggyBankUpdatePortMock;
    @Mock
    PiggyBankLoadPort piggyBankLoadPortMock;
    @Mock
    PiggyBankActivityCreatePort piggyBankActivityCreatePortMock;

    @Captor
    ArgumentCaptor<PiggyBank> piggyBankArgumentCaptor;


    @Test
    void buyingCandyViaMocks() {
        /**
         * if you don't use the extend with annotation you'll have to handle te mocks yourself.
         */
        PiggyBankUpdatePort piggyBankUpdatePort = Mockito.mock(PiggyBankUpdatePort.class);
        PiggyBankLoadPort piggyBankLoadPort = Mockito.mock(PiggyBankLoadPort.class);
        PiggyBankActivityCreatePort piggyBankActivityCreatePort = Mockito.mock(PiggyBankActivityCreatePort.class);
        BuyingCandyUseCase buyingCandyUseCase = new DefaultBuyingCandyUseCase(piggyBankUpdatePort, piggyBankLoadPort, List.of(piggyBankActivityCreatePort));
        buyingCandyUseCase.buyingCandy(new BuyCandyAmountCommand(BigDecimal.valueOf(20), new Person.PersonUUID(UUID.randomUUID())));
        Mockito.verifyNoInteractions(piggyBankActivityCreatePort);

    }

 
    @Test
    void buyingCandyViaMockUsingCaptor() {

        ActivityWindow testWindow = new ActivityWindow();
        testWindow.add(new PiggyBankActivity(PiggyBankAction.PUT_IN, BigDecimal.valueOf(50), LocalDateTime.now()));

        PiggyBank bobbiesPiggyBank = new PiggyBank(new PiggyBank.PiggyBankUUID(UUID.randomUUID()), BOBBY_ID, testWindow);

        when(piggyBankLoadPortMock.loadPiggyBankForOwner(Mockito.any(Person.PersonUUID.class))).thenReturn(Optional.of(bobbiesPiggyBank));

        BuyingCandyUseCase buyingCandyUseCase = new DefaultBuyingCandyUseCase(piggyBankUpdatePortMock, piggyBankLoadPortMock, List.of(piggyBankActivityCreatePortMock));
        buyingCandyUseCase.buyingCandy(new BuyCandyAmountCommand(BigDecimal.valueOf(20), BOBBY_ID));


        verify(piggyBankUpdatePortMock).updatePiggyBank(piggyBankArgumentCaptor.capture());

        assertEquals(BigDecimal.valueOf(30), testWindow.calculateBalance());
        assertEquals(BigDecimal.valueOf(30), piggyBankArgumentCaptor.getValue().getBalance());

    }


}
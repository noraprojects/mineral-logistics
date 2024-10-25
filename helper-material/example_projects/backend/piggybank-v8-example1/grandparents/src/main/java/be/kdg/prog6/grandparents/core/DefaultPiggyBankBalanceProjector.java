package be.kdg.prog6.grandparents.core;

import be.kdg.prog6.grandparents.domain.PiggyBank;
import be.kdg.prog6.grandparents.domain.PiggyBankAction;
import be.kdg.prog6.grandparents.events.PiggyBankActivityCreatedEvent;
import be.kdg.prog6.grandparents.ports.in.PiggyBankBalanceProjector;
import be.kdg.prog6.grandparents.ports.out.PiggyBankProjectionPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DefaultPiggyBankBalanceProjector implements PiggyBankBalanceProjector {

    private final PiggyBankProjectionPort piggyBankProjectionPort;

    public DefaultPiggyBankBalanceProjector(PiggyBankProjectionPort piggyBankProjectionPort) {
        this.piggyBankProjectionPort = piggyBankProjectionPort;
    }

    @Override
    @Transactional
    public Optional<PiggyBank> project(PiggyBankActivityCreatedEvent event) {
        Optional<PiggyBank> piggyBankOptional = piggyBankProjectionPort.loadPiggyBank(event.piggyBankUUID());
        if (piggyBankOptional.isEmpty()) {
            return Optional.empty();
        }
        PiggyBank piggyBank = piggyBankOptional.get();

        switch (PiggyBankAction.valueOf(event.action())) {
            case PUT_IN -> piggyBank.setCurrentBalance(piggyBank.getCurrentBalance().add(event.amount()));
            case TAKE_OUT -> piggyBank.setCurrentBalance(piggyBank.getCurrentBalance().subtract(event.amount()));
        }
        piggyBankProjectionPort.savePiggybank(piggyBank);

        return Optional.of(piggyBank);
    }
}

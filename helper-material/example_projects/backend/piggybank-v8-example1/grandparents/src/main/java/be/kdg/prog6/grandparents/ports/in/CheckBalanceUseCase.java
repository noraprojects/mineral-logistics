package be.kdg.prog6.grandparents.ports.in;

import be.kdg.prog6.grandparents.events.PiggyBankActivityCreatedEvent;
import org.springframework.transaction.annotation.Transactional;

public interface CheckBalanceUseCase {

    @Transactional
    void checkBalance(PiggyBankActivityCreatedEvent event);
}

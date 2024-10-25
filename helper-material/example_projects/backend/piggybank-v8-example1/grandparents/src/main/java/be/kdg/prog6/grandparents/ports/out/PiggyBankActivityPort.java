package be.kdg.prog6.grandparents.ports.out;

import be.kdg.prog6.grandparents.domain.PiggyBankActivityEvent;

public interface PiggyBankActivityPort {
    void saveState(PiggyBankActivityEvent piggyBankActivityEvent);
}

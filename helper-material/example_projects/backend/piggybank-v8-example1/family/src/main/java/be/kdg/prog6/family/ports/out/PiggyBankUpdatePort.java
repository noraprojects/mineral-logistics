package be.kdg.prog6.family.ports.out;

import be.kdg.prog6.family.domain.PiggyBank;
import be.kdg.prog6.family.domain.PiggyBankActivity;

public interface PiggyBankUpdatePort {
    void updatePiggyBank(PiggyBank piggyBank);

}

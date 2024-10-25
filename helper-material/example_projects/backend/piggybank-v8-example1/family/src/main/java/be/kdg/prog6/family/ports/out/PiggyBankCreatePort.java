package be.kdg.prog6.family.ports.out;

import be.kdg.prog6.family.domain.PiggyBank;

public interface PiggyBankCreatePort {

    void piggyBankCreated(String bobby, PiggyBank piggyBank);
}

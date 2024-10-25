package be.kdg.prog6.grandparents.ports.out;


import be.kdg.prog6.grandparents.domain.PiggyBank;

import java.util.Optional;
import java.util.UUID;

public interface PiggyBankProjectionPort {
    Optional<PiggyBank> loadPiggyBank(UUID piggyBankUUID);

    void savePiggybank(PiggyBank piggyBank);
}

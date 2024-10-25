package be.kdg.prog6.family.ports.out;

import be.kdg.prog6.family.domain.Person;
import be.kdg.prog6.family.domain.PiggyBank;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface PiggyBankLoadPort {

    Optional<PiggyBank> loadPiggyBankForOwner(Person.PersonUUID ownerUuid);

}

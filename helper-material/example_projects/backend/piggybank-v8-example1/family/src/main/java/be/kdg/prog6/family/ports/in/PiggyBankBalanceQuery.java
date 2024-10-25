package be.kdg.prog6.family.ports.in;

import be.kdg.prog6.family.domain.Person;

import java.math.BigDecimal;

public interface PiggyBankBalanceQuery {

    BigDecimal getBalance(PiggyBankBalanceCommand piggyBankBalanceCommand);

    BigDecimal getBalanceForPiggyBankOwner(Person.PersonUUID personUUID);
}

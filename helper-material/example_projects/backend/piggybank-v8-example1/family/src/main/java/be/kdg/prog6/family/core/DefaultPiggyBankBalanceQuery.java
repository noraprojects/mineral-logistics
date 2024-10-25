package be.kdg.prog6.family.core;

import be.kdg.prog6.family.domain.Person;
import be.kdg.prog6.family.domain.PiggyBank;
import be.kdg.prog6.family.ports.in.PiggyBankBalanceCommand;
import be.kdg.prog6.family.ports.in.PiggyBankBalanceQuery;
import be.kdg.prog6.family.ports.out.PiggyBankActivityLoadPort;
import be.kdg.prog6.family.ports.out.PiggyBankLoadPort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class DefaultPiggyBankBalanceQuery implements PiggyBankBalanceQuery {
    private final PiggyBankLoadPort piggyBankLoadPort;
    private final PiggyBankActivityLoadPort piggyBankActivityLoadPort;

    public DefaultPiggyBankBalanceQuery(PiggyBankLoadPort piggyBankLoadPort, PiggyBankActivityLoadPort piggyBankActivityLoadPort) {
        this.piggyBankLoadPort = piggyBankLoadPort;
        this.piggyBankActivityLoadPort = piggyBankActivityLoadPort;
    }

    @Override
    public BigDecimal getBalance(PiggyBankBalanceCommand piggyBankBalanceCommand) {
        Optional<PiggyBank> piggyBank = piggyBankActivityLoadPort.loadPiggyBank(piggyBankBalanceCommand.owner(), piggyBankBalanceCommand.start(),piggyBankBalanceCommand.end());
        return piggyBank.map(PiggyBank::getBalance).orElse(BigDecimal.ZERO);
    }

    @Override
    public BigDecimal getBalanceForPiggyBankOwner(Person.PersonUUID personUUID) {
        Optional<PiggyBank> piggyBank = piggyBankLoadPort.loadPiggyBankForOwner(personUUID);
        return piggyBank.map(PiggyBank::getBalance).orElse(BigDecimal.ZERO);
    }
}

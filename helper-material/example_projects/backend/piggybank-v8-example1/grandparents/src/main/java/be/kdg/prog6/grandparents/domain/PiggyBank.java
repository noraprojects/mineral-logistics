package be.kdg.prog6.grandparents.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class PiggyBank {

    private static final BigDecimal MAX_BALANCE = BigDecimal.valueOf(50);

    private final PiggyBankUUID piggyBankUUID;
    private final PersonUUID personUUID;

    private BigDecimal currentBalance = BigDecimal.ZERO;


    public PiggyBank(PiggyBankUUID piggyBankUUID, PersonUUID personUUID) {
        this.piggyBankUUID = piggyBankUUID;
        this.personUUID = personUUID;
    }

    public record PiggyBankUUID(UUID uuid) {
    }

    public record PersonUUID(UUID uuid) {
    }

    public PersonUUID getPersonUUID() {
        return personUUID;
    }

    public PiggyBankUUID getPiggyBankUUID() {
        return piggyBankUUID;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal balance) {
        currentBalance = balance;
    }

    public boolean isItTimeToGoShopping() {
        return getCurrentBalance().compareTo(MAX_BALANCE) >= 0;
    }
}

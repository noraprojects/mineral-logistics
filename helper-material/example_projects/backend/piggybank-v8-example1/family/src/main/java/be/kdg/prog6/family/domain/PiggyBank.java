package be.kdg.prog6.family.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class PiggyBank {

    private PiggyBankUUID piggyBankUUID;


    private BigDecimal baseBalance = BigDecimal.ZERO;
    private LocalDateTime baseBalanceDate;
    private ActivityWindow activityWindow;


    private final Person.PersonUUID owner;

    public PiggyBank(PiggyBankUUID piggyBankUUID, Person.PersonUUID owner, ActivityWindow activityWindow) {
        this.piggyBankUUID = piggyBankUUID;
        this.owner = owner;
        this.activityWindow = activityWindow;
        this.baseBalanceDate = null;
        this.baseBalance = BigDecimal.ZERO;

    }

    public PiggyBank(PiggyBankUUID piggyBankUUID, Person.PersonUUID owner, ActivityWindow activityWindow, BigDecimal baseBalance, LocalDateTime baseBalanceDate) {
        this(piggyBankUUID, owner, activityWindow);
        this.baseBalance = baseBalance;
        this.baseBalanceDate = baseBalanceDate;
    }

    public record PiggyBankUUID(UUID uuid) {
    }


    public PiggyBankActivity putMoney(BigDecimal money) {
        PiggyBankActivity activity = new PiggyBankActivity(PiggyBankAction.PUT_IN, money, LocalDateTime.now());
        activityWindow.add(activity);
        return activity;
    }

    public Optional<PiggyBankActivity> takeMoney(BigDecimal money) {
        if ((getBalance().compareTo(money) >= 0) && (money.compareTo(BigDecimal.ZERO) > 0)) {
            PiggyBankActivity activity = new PiggyBankActivity(PiggyBankAction.TAKE_OUT, money, LocalDateTime.now());
            activityWindow.add(activity);
            return Optional.of(activity);

        }
        return Optional.empty();
    }

    public BigDecimal getBalance() {
        System.out.println(">>>>>>>>> Start of window: " + activityWindow.getStartTimestamp());
        return baseBalance.add(activityWindow.calculateBalance());
    }


    public void snapshotBalance() {
        baseBalance = getBalance();
        baseBalanceDate = LocalDateTime.now();//make sure you are in an UTC computed environment
    }

    public BigDecimal getBaseBalance() {
        return baseBalance;
    }

    public LocalDateTime getBaseBalanceDate() {
        return baseBalanceDate;
    }

    public Person.PersonUUID getOwner() {
        return owner;
    }

    public PiggyBankUUID getPiggyBankUUID() {
        return piggyBankUUID;
    }

    public void addPiggyBankActivity(PiggyBankActivity piggyBankActivity) {
        this.activityWindow.add(piggyBankActivity);
    }


}

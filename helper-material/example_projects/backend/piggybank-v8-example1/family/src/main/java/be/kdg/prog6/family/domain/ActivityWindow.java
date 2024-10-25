package be.kdg.prog6.family.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ActivityWindow {

    private final List<PiggyBankActivity> activities = new ArrayList<>();


    public LocalDateTime getStartTimestamp() {
        return activities.stream()
                .min(Comparator.comparing(PiggyBankActivity::pit))
                .orElseThrow(IllegalStateException::new).pit();
    }

    public LocalDateTime getEndTimestamp() {
        return activities.stream()
                .max(Comparator.comparing(PiggyBankActivity::pit))
                .orElseThrow(IllegalStateException::new)
                .pit();
    }

    public BigDecimal calculateBalance() {
        BigDecimal amount = BigDecimal.ZERO;
        for (PiggyBankActivity activity : activities) {
            if (activity.action() == PiggyBankAction.PUT_IN) {
                amount = amount.add(activity.amount());
            } else {
                amount = amount.subtract(activity.amount());
            }
        }
        return amount;
    }

    public boolean add(PiggyBankActivity activity){
        return activities.add(activity);
    }
}

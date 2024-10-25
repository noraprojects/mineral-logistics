package be.kdg.prog6.ticketing.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ActivityWindow {

    private final List<TicketingActivity> activities = new ArrayList<>();


    public LocalDateTime getStartTimestamp() {
        return activities.stream()
                .min(Comparator.comparing(TicketingActivity::pit))
                .orElseThrow(IllegalStateException::new).pit();
    }

    public LocalDateTime getEndTimestamp() {
        return activities.stream()
                .max(Comparator.comparing(TicketingActivity::pit))
                .orElseThrow(IllegalStateException::new)
                .pit();
    }


    public boolean add(TicketingActivity activity){
        return activities.add(activity);
    }
}

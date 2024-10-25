package be.kdg.prog6.parkManagement.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ActivityWindow {

    private final List<VisitorActivity> activities = new ArrayList<>();


    public LocalDateTime getStartTimestamp() {
        return activities.stream()
                .min(Comparator.comparing(VisitorActivity::pit))
                .orElseThrow(IllegalStateException::new).pit();
    }

    public LocalDateTime getEndTimestamp() {
        return activities.stream()
                .max(Comparator.comparing(VisitorActivity::pit))
                .orElseThrow(IllegalStateException::new)
                .pit();
    }

    public int calculateVisitorCount() {
        int visitorCount = 0;
        for (VisitorActivity activity : activities) {
            if (activity.action() == VisitorAction.Enter) {
                visitorCount++;
            } else if (activity.action() == VisitorAction.Leave && visitorCount > 0) {
                visitorCount--;
            }
        }
        return visitorCount;
    }


    public int calculateDailyVisitorCount() {
        int visitorCount = 0;
        for (VisitorActivity activity : activities) {
            if (activity.action() == VisitorAction.Enter && activity.pit() != null && Park.getInstance().getSnapShotDate() != null && activity.pit().isAfter(Park.getInstance().getSnapShotDate())) {
                visitorCount++;
            }
        }
        return visitorCount;
    }


    public boolean add(VisitorActivity activity) {
        return activities.add(activity);
    }
}

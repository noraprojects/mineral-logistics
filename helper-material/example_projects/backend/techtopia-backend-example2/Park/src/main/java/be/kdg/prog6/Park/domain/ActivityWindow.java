package be.kdg.prog6.Park.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ActivityWindow {

    private final List<AttractionQueueActivity> activities = new ArrayList<>();

    public LocalDateTime getStartTimestamp() {
        return activities.stream()
                .min(Comparator.comparing(AttractionQueueActivity::pit))
                .orElseThrow(IllegalStateException::new).pit();
    }

    public LocalDateTime getEndTimestamp() {
        return activities.stream()
                .max(Comparator.comparing(AttractionQueueActivity::pit))
                .orElseThrow(IllegalStateException::new)
                .pit();
    }

    public int calculateVisitors() {
        int visitorCount = 0;

        for (AttractionQueueActivity activity : activities) {
            if (activity.action() == QueueAction.Enter) {
                visitorCount++;
            } else if (activity.action() == QueueAction.Leave && visitorCount > 0) {
                visitorCount--;
            }
        }

        return visitorCount;
    }


    public List<AttractionQueueActivity> getActivitiesForUUID(UUID uuid) {
        return activities.stream()
                .filter(activity -> activity.attractionUUID().uuid().equals(uuid))
                .collect(Collectors.toList());
    }

    public List<AttractionQueueActivity> getActivities() {
        return activities;
    }

    public boolean add(AttractionQueueActivity activity) {
        return activities.add(activity);
    }
}

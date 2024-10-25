package be.kdg.prog6.parkManagement.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class StandActivityWindow {

    private final List<RefreshmentsActivity> activities = new ArrayList<>();


    public LocalDateTime getStartTimestamp() {
        return activities.stream()
                .min(Comparator.comparing(RefreshmentsActivity::pit))
                .orElseThrow(IllegalStateException::new).pit();
    }

    public LocalDateTime getEndTimestamp() {
        return activities.stream()
                .max(Comparator.comparing(RefreshmentsActivity::pit))
                .orElseThrow(IllegalStateException::new)
                .pit();
    }

    public int calculateStandsAmount() {
        int count = 0;
        for (RefreshmentsActivity activity : activities) {
            if (activity.action() == RefreshmentStandAction.OPEN) {
                count++;
            } else {
                if (count > 0) {
                    count--;
                }
            }
        }
        return count;
    }

    public List<RefreshmentsActivity> getActivitiesForUUID(UUID uuid) {
        return activities.stream()
                .filter(activity -> activity.uuid().equals(uuid))
                .collect(Collectors.toList());
    }

    public List<RefreshmentsActivity> getAll() {
        return activities;
    }

    public boolean add(RefreshmentsActivity activity) {
        return activities.add(activity);
    }
}

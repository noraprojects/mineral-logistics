package be.kdg.prog6.family.domain;

import java.util.ArrayList;
import java.util.List;

public class AgendaWindow {

    private final List activityList = new ArrayList();

    public void addActivity(AgendaActivity activity) {
        activityList.add(activity);
    }

    public List getActivityList() {
        return activityList;
    }
}

package be.kdg.prog6.parkManagement.ports.out;

import be.kdg.prog6.parkManagement.domain.VisitorActivity;

import java.util.List;

public interface VisitorsLoadPort {

    int getVisitorsCount();
    List<VisitorActivity> getVisitorActivities();

}

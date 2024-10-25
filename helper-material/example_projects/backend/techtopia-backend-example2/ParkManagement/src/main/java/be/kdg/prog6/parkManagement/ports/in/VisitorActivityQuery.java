package be.kdg.prog6.parkManagement.ports.in;

import be.kdg.prog6.parkManagement.domain.VisitorActivity;

import java.util.List;

public interface VisitorActivityQuery {
    List<VisitorActivity> getActivities();
}

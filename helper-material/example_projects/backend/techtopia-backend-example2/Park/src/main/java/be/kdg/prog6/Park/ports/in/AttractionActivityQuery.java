package be.kdg.prog6.Park.ports.in;

import be.kdg.prog6.Park.domain.AttractionQueueActivity;

import java.util.List;

public interface AttractionActivityQuery {
    List<AttractionQueueActivity> getActivities();
}

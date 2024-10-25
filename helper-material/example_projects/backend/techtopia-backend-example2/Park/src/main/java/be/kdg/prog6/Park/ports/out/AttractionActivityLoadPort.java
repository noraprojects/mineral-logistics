package be.kdg.prog6.Park.ports.out;

import be.kdg.prog6.Park.domain.AttractionQueueActivity;

import java.util.List;

public interface AttractionActivityLoadPort {
    List<AttractionQueueActivity> getActivities();
}

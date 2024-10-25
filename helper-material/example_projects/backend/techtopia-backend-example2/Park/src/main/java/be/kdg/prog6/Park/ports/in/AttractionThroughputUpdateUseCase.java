package be.kdg.prog6.Park.ports.in;

import be.kdg.prog6.Park.events.ParkReceivedStandEvent;
import be.kdg.prog6.Park.events.ParkReceivedThroughputEvent;

public interface AttractionThroughputUpdateUseCase {
    void update(ParkReceivedThroughputEvent command);

}

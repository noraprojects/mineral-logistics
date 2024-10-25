package be.kdg.prog6.Park.ports.in;

import be.kdg.prog6.Park.domain.RefreshmentStand;
import be.kdg.prog6.Park.events.ParkReceivedStandEvent;

import java.util.Optional;

public interface StandReceivedProjector {
    Optional<RefreshmentStand> project(ParkReceivedStandEvent event);
}




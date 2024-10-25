package be.kdg.prog6.Park.core;

import be.kdg.prog6.Park.domain.RefreshmentStand;
import be.kdg.prog6.Park.events.ParkDeleteStandEvent;
import be.kdg.prog6.Park.ports.in.StandDeletedProjector;
import be.kdg.prog6.Park.ports.out.StandDeletePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultStandDeletedProjector implements StandDeletedProjector {
    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultStandDeletedProjector.class);

    private final StandDeletePort standDeletePort;

    public DefaultStandDeletedProjector(StandDeletePort standDeletePort) {
        this.standDeletePort = standDeletePort;
    }

    @Override
    public Optional<RefreshmentStand> project(ParkDeleteStandEvent event) {
        LOGGER.info("deleting stand "+ event.standUUID());
        standDeletePort.delete(event.standUUID());
        return Optional.empty();
    }
}

package be.kdg.prog6.Park.core;

import be.kdg.prog6.Park.domain.RefreshmentStand;
import be.kdg.prog6.Park.events.ParkReceivedStandEvent;
import be.kdg.prog6.Park.ports.in.StandReceivedProjector;
import be.kdg.prog6.Park.ports.out.StandCreatePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultStandReceivedProjector implements StandReceivedProjector {
    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultStandReceivedProjector.class);

    private final StandCreatePort standCreatePort;

    public DefaultStandReceivedProjector(StandCreatePort standCreatePort) {
        this.standCreatePort = standCreatePort;
    }


    @Override
    public Optional<RefreshmentStand> project(ParkReceivedStandEvent event) {
        LOGGER.info("Stand received by the park");
        standCreatePort.create(event.standUUID());
        return Optional.empty();
    }
}

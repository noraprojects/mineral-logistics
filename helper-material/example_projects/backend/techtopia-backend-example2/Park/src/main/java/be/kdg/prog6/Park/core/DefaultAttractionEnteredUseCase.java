package be.kdg.prog6.Park.core;

import be.kdg.prog6.Park.domain.Attraction;
import be.kdg.prog6.Park.domain.AttractionQueueActivity;
import be.kdg.prog6.Park.ports.in.AttractionEnteredUseCase;
import be.kdg.prog6.Park.ports.in.EnterAttractionCommand;
import be.kdg.prog6.Park.ports.out.AttractionCreatePort;
import be.kdg.prog6.Park.ports.out.AttractionQueueActivityCreatePort;
import be.kdg.prog6.Park.ports.out.AttractionQueueLoadPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultAttractionEnteredUseCase implements AttractionEnteredUseCase {


    private final AttractionQueueLoadPort attractionQueueLoadPort;
    private final List<AttractionQueueActivityCreatePort> ticketingActivityCreatePorts;
    private final AttractionCreatePort attractionCreatePort;

    public DefaultAttractionEnteredUseCase(AttractionQueueLoadPort attractionQueueLoadPort, List<AttractionQueueActivityCreatePort> ticketingActivityCreatePorts, AttractionCreatePort attractionCreatePort) {
        this.attractionQueueLoadPort = attractionQueueLoadPort;
        this.ticketingActivityCreatePorts = ticketingActivityCreatePorts;
        this.attractionCreatePort = attractionCreatePort;
    }


    @Override
    public void enterAttraction(EnterAttractionCommand enterAttractionCommand) {
        Optional<Attraction> optionalAttraction = attractionQueueLoadPort.loadAttractionByUuid(enterAttractionCommand.attractionUUID());
        optionalAttraction.ifPresentOrElse(
                attraction -> {
                    if (attraction.getVisitorCount() >= 3 && attraction.getVisitorQueue().getVisitorAdditionRate() != 80) {
                        // Publish the event when the queue size reaches or exceeds 80
                        attractionCreatePort.attractionQeueuReached(attraction);
                    }

                    AttractionQueueActivity activity = attraction.admitVisitor(enterAttractionCommand.tickerUUID(), attraction.getAttractionUUID());
                    ticketingActivityCreatePorts.forEach(port -> port.createAttractionQueueActivity(enterAttractionCommand.tickerUUID(), activity));
                },
                () -> {
                    System.out.println("attraction not found");
                }
        );
    }

}

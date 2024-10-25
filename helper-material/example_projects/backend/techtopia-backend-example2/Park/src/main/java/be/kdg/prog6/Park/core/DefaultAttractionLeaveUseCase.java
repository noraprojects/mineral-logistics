package be.kdg.prog6.Park.core;

import be.kdg.prog6.Park.domain.Attraction;
import be.kdg.prog6.Park.domain.AttractionQueueActivity;
import be.kdg.prog6.Park.ports.in.AttractionLeaveUseCase;
import be.kdg.prog6.Park.ports.in.LeaveAttractionCommand;
import be.kdg.prog6.Park.ports.out.AttractionQueueActivityCreatePort;
import be.kdg.prog6.Park.ports.out.AttractionQueueLoadPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultAttractionLeaveUseCase implements AttractionLeaveUseCase {

    private final AttractionQueueLoadPort attractionQueueLoadPort;
    private final List<AttractionQueueActivityCreatePort> ticketingActivityCreatePorts;

    public DefaultAttractionLeaveUseCase(AttractionQueueLoadPort attractionQueueLoadPort, List<AttractionQueueActivityCreatePort> ticketingActivityCreatePorts) {
        this.attractionQueueLoadPort = attractionQueueLoadPort;
        this.ticketingActivityCreatePorts = ticketingActivityCreatePorts;
    }


    @Override
    public void leaveAttraction(LeaveAttractionCommand leaveAttractionCommand) {
        Optional<Attraction> optionalAttraction = attractionQueueLoadPort.loadAttractionByUuid(leaveAttractionCommand.attractionUUID());
        optionalAttraction.ifPresentOrElse(
                attraction -> {
                    AttractionQueueActivity activity = attraction.dischargeVisitor(leaveAttractionCommand.tickerUUID(), attraction.getAttractionUUID());
                    ticketingActivityCreatePorts.forEach(port -> port.createAttractionQueueActivity(leaveAttractionCommand.tickerUUID(), activity));
                },
                () -> {
                    System.out.println("Attraction not found for UUID: " + leaveAttractionCommand.attractionUUID());
                    throw new RuntimeException("Attraction not found for UUID: " + leaveAttractionCommand.attractionUUID());
                }
        );
    }
}

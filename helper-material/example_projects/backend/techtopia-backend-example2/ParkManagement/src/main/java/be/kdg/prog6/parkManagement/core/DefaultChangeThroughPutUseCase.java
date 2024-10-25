package be.kdg.prog6.parkManagement.core;

import be.kdg.prog6.parkManagement.ports.in.ChangeThroughPutCommand;
import be.kdg.prog6.parkManagement.ports.in.ChangeThroughputUseCase;
import be.kdg.prog6.parkManagement.ports.out.AttractionUpdatePort;
import org.springframework.stereotype.Service;

@Service
public class DefaultChangeThroughPutUseCase implements ChangeThroughputUseCase {

    private final AttractionUpdatePort attractionUpdatePort;

    public DefaultChangeThroughPutUseCase(AttractionUpdatePort attractionUpdatePort) {
        this.attractionUpdatePort = attractionUpdatePort;
    }

    @Override
    public void changeThroughput(ChangeThroughPutCommand getTicketCommand) {
        attractionUpdatePort.updateThroughput(getTicketCommand.attractionID());
    }
}

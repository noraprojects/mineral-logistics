package be.kdg.prog6.parkManagement.ports.out;

import be.kdg.prog6.parkManagement.domain.Visitor;
import be.kdg.prog6.parkManagement.domain.VisitorActivity;

public interface ParkActivityCreatePort {
    void createParkActivity(Visitor.ticketUUID ticketUUID, VisitorActivity visitorActivity);

}

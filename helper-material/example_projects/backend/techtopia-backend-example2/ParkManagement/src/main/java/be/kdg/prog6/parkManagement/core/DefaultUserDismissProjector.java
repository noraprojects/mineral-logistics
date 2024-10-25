package be.kdg.prog6.parkManagement.core;

import be.kdg.prog6.parkManagement.domain.Park;
import be.kdg.prog6.parkManagement.domain.Visitor;
import be.kdg.prog6.parkManagement.domain.VisitorActivity;
import be.kdg.prog6.parkManagement.events.UserDismissedActivityEvent;
import be.kdg.prog6.parkManagement.ports.in.UserDissmissProjector;
import be.kdg.prog6.parkManagement.ports.out.ParkActivityCreatePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultUserDismissProjector implements UserDissmissProjector {

    private final List<ParkActivityCreatePort> parkActivityCreatePorts;

    public DefaultUserDismissProjector(List<ParkActivityCreatePort> parkActivityCreatePorts) {
        this.parkActivityCreatePorts = parkActivityCreatePorts;
    }


    @Override
    public void dismissUser(UserDismissedActivityEvent event) {
        VisitorActivity activity = Park.getInstance().decreaseDailyVisitorCount(event.codeP());
        parkActivityCreatePorts.forEach(port -> port.createParkActivity(new Visitor.ticketUUID(event.codeP()), activity));

    }
}

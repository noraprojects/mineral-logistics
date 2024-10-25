package be.kdg.prog6.parkManagement.core;


import be.kdg.prog6.parkManagement.domain.Park;
import be.kdg.prog6.parkManagement.domain.Visitor;
import be.kdg.prog6.parkManagement.domain.VisitorActivity;
import be.kdg.prog6.parkManagement.events.UserAdmittedActivityEvent;
import be.kdg.prog6.parkManagement.ports.in.UserAdmittedProjector;
import be.kdg.prog6.parkManagement.ports.out.ParkActivityCreatePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultUserAdmittedProjector implements UserAdmittedProjector {

    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultUserAdmittedProjector.class);

    private final List<ParkActivityCreatePort> parkActivityCreatePorts;


    public DefaultUserAdmittedProjector(List<ParkActivityCreatePort> parkActivityCreatePorts) {
        this.parkActivityCreatePorts = parkActivityCreatePorts;
    }

    @Override
    public void admitUser(UserAdmittedActivityEvent event) {
        VisitorActivity activity = Park.getInstance().incrementDailyVisitorCount(event.codeP());
        parkActivityCreatePorts.forEach(port -> port.createParkActivity(new Visitor.ticketUUID(event.codeP()), activity));


    }
}

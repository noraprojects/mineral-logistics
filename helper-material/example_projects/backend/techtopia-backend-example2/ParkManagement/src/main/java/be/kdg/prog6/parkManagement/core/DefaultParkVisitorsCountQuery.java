package be.kdg.prog6.parkManagement.core;

import be.kdg.prog6.parkManagement.ports.in.ParkVisitorsCountQuery;
import be.kdg.prog6.parkManagement.ports.out.VisitorsLoadPort;
import org.springframework.stereotype.Service;

@Service
public class DefaultParkVisitorsCountQuery implements ParkVisitorsCountQuery {


    private final VisitorsLoadPort visitorsLoadPort;

    public DefaultParkVisitorsCountQuery(VisitorsLoadPort visitorsLoadPort) {
        this.visitorsLoadPort = visitorsLoadPort;
    }

    @Override
    public int getVisitorsCount() {
        return visitorsLoadPort.getVisitorsCount();
    }
}

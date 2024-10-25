package be.kdg.prog6.parkManagement.core;

import be.kdg.prog6.parkManagement.domain.VisitorActivity;
import be.kdg.prog6.parkManagement.ports.in.VisitorActivityQuery;
import be.kdg.prog6.parkManagement.ports.out.VisitorsLoadPort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DefaultVisitorActivityQuery implements VisitorActivityQuery {

    private final VisitorsLoadPort visitorsLoadPort;

    public DefaultVisitorActivityQuery(VisitorsLoadPort visitorsLoadPort) {
        this.visitorsLoadPort = visitorsLoadPort;
    }

    @Override
    public List<VisitorActivity> getActivities() {
       return visitorsLoadPort.getVisitorActivities();
    }
}

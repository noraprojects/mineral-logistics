package be.kdg.prog6.parkManagement.adapters.out.db;

import be.kdg.prog6.parkManagement.domain.Park;
import be.kdg.prog6.parkManagement.domain.Visitor;
import be.kdg.prog6.parkManagement.domain.VisitorActivity;
import be.kdg.prog6.parkManagement.ports.out.ParkActivityCreatePort;
import be.kdg.prog6.parkManagement.ports.out.ParkUpdatePort;
import be.kdg.prog6.parkManagement.ports.out.VisitorsLoadPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ParkDBAdapter implements VisitorsLoadPort, ParkActivityCreatePort, ParkUpdatePort {


    private static final Logger LOGGER = LoggerFactory.getLogger(ParkDBAdapter.class);
    private final ParkActivityRepository parkActivityRepository;
    private final ParkRepository parkRepository;

    public ParkDBAdapter(ParkActivityRepository parkActivityRepository, ParkRepository parkRepository) {
        this.parkActivityRepository = parkActivityRepository;
        this.parkRepository = parkRepository;
    }


    @Override
    public void updatePark() {
        ParkJpaEntity parkJpaEntity = new ParkJpaEntity();
        parkJpaEntity.setPeopleCount(this.getVisitorsCount());
        parkJpaEntity.setSnapshotTimestamp(Park.getInstance().getSnapShotDate());
        parkRepository.save(parkJpaEntity);
    }


    @Override
    public int getVisitorsCount() {
        LOGGER.info("printing count");
        return Park.getInstance().getCurrentVisitorCount();
    }

    @Override
    public List<VisitorActivity> getVisitorActivities() {
        List<ParkJpaActivity> parkActivities = parkActivityRepository.findAll();
        List<VisitorActivity> visitorActivities = new ArrayList<>();
        for (ParkJpaActivity parkActivity : parkActivities) {
            VisitorActivity visitorActivity = new VisitorActivity(parkActivity.getVisitorAction(), parkActivity.getUser(), parkActivity.getPit());
            visitorActivities.add(visitorActivity);
        }
        return visitorActivities;
    }


    @Override
    public void createParkActivity(Visitor.ticketUUID ticketUUID, VisitorActivity visitorActivity) {
        ParkJpaActivity parkJpaActivity = new ParkJpaActivity();
        parkJpaActivity.setVisitorAction(visitorActivity.action());
        parkJpaActivity.setPit(visitorActivity.pit());
        parkJpaActivity.setUser(visitorActivity.user());
        parkActivityRepository.save(parkJpaActivity);
    }


    //    @Scheduled(cron = "0 0 0 * * *") // runs at 00:00 every day
    @Scheduled(fixedRate = 300_000) // 300000 milliseconds = 5 minutes for exam
    public void createSnapshot() {
        System.out.println("Creating snapshot...");
        Park.getInstance().snapShotVisitorCount();
        this.updatePark();
    }
}


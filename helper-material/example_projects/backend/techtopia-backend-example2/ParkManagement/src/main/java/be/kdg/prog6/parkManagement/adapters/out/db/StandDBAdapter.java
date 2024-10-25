package be.kdg.prog6.parkManagement.adapters.out.db;

import be.kdg.prog6.parkManagement.domain.RefreshmentsActivity;
import be.kdg.prog6.parkManagement.ports.out.RefreshmentStandActivityCreatePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class StandDBAdapter implements RefreshmentStandActivityCreatePort {

    public static final Logger log = LoggerFactory.getLogger(StandDBAdapter.class);
    private final RefreshmentStandActivityRepository ActivityRepository;


    public StandDBAdapter(RefreshmentStandActivityRepository activityRepository) {
        ActivityRepository = activityRepository;

    }

    @Override
    public void createRSActivity(UUID standUUID, RefreshmentsActivity refreshmentstandActivity) {
        RefreshmentStandJpaActivity rsJpaActivity = new RefreshmentStandJpaActivity();
        rsJpaActivity.setAction(refreshmentstandActivity.action());
        rsJpaActivity.setPit(refreshmentstandActivity.pit());
        rsJpaActivity.setStandID(standUUID);
        ActivityRepository.save(rsJpaActivity);

    }

}

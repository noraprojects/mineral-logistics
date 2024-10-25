package be.kdg.prog6.Park.adapters.out.db;

import be.kdg.prog6.Park.domain.Attraction;
import be.kdg.prog6.Park.domain.AttractionQueueActivity;
import be.kdg.prog6.Park.domain.Ticket;
import be.kdg.prog6.Park.ports.out.AttractionActivityLoadPort;
import be.kdg.prog6.Park.ports.out.AttractionQueueActivityCreatePort;
import be.kdg.prog6.Park.ports.out.AttractionQueueLoadPort;
import be.kdg.prog6.Park.ports.out.AttractionThroughputUpdatePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AttractionQueueDBAdapter implements AttractionActivityLoadPort, AttractionQueueLoadPort, AttractionQueueActivityCreatePort, AttractionThroughputUpdatePort {


    private static final Logger LOGGER = LoggerFactory.getLogger(AttractionQueueDBAdapter.class);

    private final AttractionQueueRepository attractionQueueRepository;
    private final AttractionQueueActivityRepository attractionQueueActivityRepository;


    public AttractionQueueDBAdapter(AttractionQueueRepository attractionQueueRepository, AttractionQueueActivityRepository attractionQueueActivityRepository) {
        this.attractionQueueRepository = attractionQueueRepository;
        this.attractionQueueActivityRepository = attractionQueueActivityRepository;
    }

    @Override
    public void createAttractionQueueActivity(Ticket.TicketUUID ticketUUID, AttractionQueueActivity attractionActivity) {
        AttractionQueueJpaActivity attractionJPaEntity = new AttractionQueueJpaActivity();
        attractionJPaEntity.setTicketingAction(attractionActivity.action());
        attractionJPaEntity.setPit(attractionActivity.pit());
        attractionJPaEntity.setOwner(ticketUUID.uuid());
        attractionJPaEntity.setAttraction(attractionActivity.attractionUUID().uuid());
        attractionQueueActivityRepository.save(attractionJPaEntity);
    }

    @Override
    public Optional<Attraction> loadAttractionByUuid(Attraction.AttractionUUID attractionUuid) {
        Optional<AttractionJpaEntity> attractionsJpaEntity = attractionQueueRepository.findByUuid(attractionUuid.uuid());

        if (attractionsJpaEntity.isEmpty()) {
            return Optional.empty();
        }


        Attraction attraction = new Attraction(attractionsJpaEntity.get().getName(), 0, attractionUuid, attractionsJpaEntity.get().getText(),
                attractionsJpaEntity.get().getCategory(), attractionsJpaEntity.get().getTag(), attractionsJpaEntity.get().getAgeGroup(), attractionsJpaEntity.get().getAttractionStatus(), attractionsJpaEntity.get().getThroughput(), attractionsJpaEntity.get().getMiniumHeight(), attractionsJpaEntity.get().getImage());


        List<AttractionQueueJpaActivity> attractionBankJpaActivityList = null;

        attractionBankJpaActivityList = attractionQueueActivityRepository.findByattraction(attraction.getAttractionUUID().uuid());

        for (AttractionQueueJpaActivity Activity : attractionBankJpaActivityList) {
            attraction.addAttractionActivity(new AttractionQueueActivity(Activity.getTicketingAction(), new Ticket.TicketUUID(Activity.getOwner()), Activity.getPit(), attractionUuid));
        }
        return Optional.of(attraction);

    }

    @Override
    public List<Attraction> loadAllAttractions() {
        List<AttractionJpaEntity> attractionEntities = attractionQueueRepository.findAll();
        List<Attraction> attractions = new ArrayList<>();

        for (AttractionJpaEntity entity : attractionEntities) {
            System.out.println(entity.getName());
            Attraction attraction = convertToAttraction(entity);
            System.out.println(attraction.getName());
            attractions.add(attraction);
        }

        return attractions;
    }


    private Attraction convertToAttraction(AttractionJpaEntity entity) {
        Attraction.AttractionUUID attractionUuid = new Attraction.AttractionUUID(entity.getUuid());

        // Create the Attraction domain object from the entity
        Attraction attraction = new Attraction(
                entity.getName(),
                0, // You might want to replace this with the actual value
                attractionUuid,
                entity.getText(),
                entity.getCategory(),
                entity.getTag(),
                entity.getAgeGroup(),
                entity.getAttractionStatus(),
                entity.getThroughput(),
                entity.getMiniumHeight(),
                entity.getImage()
        );

        // Now, we fetch the related activities for this attraction.
        List<AttractionQueueJpaActivity> attractionActivityEntities =
                attractionQueueActivityRepository.findByattraction(attraction.getAttractionUUID().uuid());

        // Convert the activity entities to domain objects and add them to the attraction
        for (AttractionQueueJpaActivity activityEntity : attractionActivityEntities) {
            AttractionQueueActivity activity = new AttractionQueueActivity(
                    activityEntity.getTicketingAction(),
                    new Ticket.TicketUUID(activityEntity.getOwner()),
                    activityEntity.getPit(),
                    attractionUuid
            );

            attraction.addAttractionActivity(activity);
        }

        return attraction;
    }


    @Override
    public void update(Attraction.AttractionUUID attractionUuid) {
        Optional<AttractionJpaEntity> attractionsJpaEntity = attractionQueueRepository.findByUuid(attractionUuid.uuid());
        attractionsJpaEntity.get().setThroughput(80);
        attractionQueueRepository.save(attractionsJpaEntity.get());
    }

    @Override
    public List<AttractionQueueActivity> getActivities() {
        List<AttractionQueueJpaActivity> activities = attractionQueueActivityRepository.findAll();
        List<AttractionQueueActivity> attractionQueueActivities = new ArrayList<>();


        for (AttractionQueueJpaActivity activity : activities) {
            AttractionQueueActivity attractionQueueActivity = new AttractionQueueActivity(
                    activity.getTicketingAction(), new Ticket.TicketUUID(activity.getOwner()), activity.getPit(),
                    new Attraction.AttractionUUID(activity.getAttraction()));
            attractionQueueActivities.add(attractionQueueActivity);
        }
        return attractionQueueActivities;

    }


}

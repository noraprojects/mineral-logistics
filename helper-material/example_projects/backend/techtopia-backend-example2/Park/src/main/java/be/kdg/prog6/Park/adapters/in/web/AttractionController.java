package be.kdg.prog6.Park.adapters.in.web;

import be.kdg.prog6.Park.adapters.in.AttractionDto;
import be.kdg.prog6.Park.domain.*;
import be.kdg.prog6.Park.ports.in.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class AttractionController {

    private final AttractionEnteredUseCase attractionEnteredUseCase;
    private final AttractionQueueSizeQuery attractionQueueSizeQuery;
    private final AttractionEnitityQuery attractionEnitityQuery;
    private final AttractionActivityQuery attractionActivityQuery;
    private final AttractionLeaveUseCase attractionLeaveUseCase;


    public AttractionController(AttractionEnteredUseCase attractionEnteredUseCase, AttractionQueueSizeQuery attractionQueueSizeQuery, AttractionEnitityQuery attractionEnitityQuery, AttractionActivityQuery attractionActivityQuery, AttractionLeaveUseCase attractionLeaveUseCase) {
        this.attractionEnteredUseCase = attractionEnteredUseCase;
        this.attractionQueueSizeQuery = attractionQueueSizeQuery;
        this.attractionEnitityQuery = attractionEnitityQuery;
        this.attractionActivityQuery = attractionActivityQuery;
        this.attractionLeaveUseCase = attractionLeaveUseCase;
    }


    @PostMapping("/ticket/{tickerUUID}/attraction/{attractionUUID}")
    public void attractionQueued(@PathVariable UUID tickerUUID, @PathVariable UUID attractionUUID) {

        attractionEnteredUseCase.enterAttraction(new EnterAttractionCommand(new Ticket.TicketUUID(tickerUUID), new Attraction.AttractionUUID(attractionUUID)));
    }

    @PostMapping("/leave/ticket/{ticketUUID}/attraction/{attractionUUID}")
    public ResponseEntity<String> leaveAttraction(@PathVariable UUID ticketUUID, @PathVariable UUID attractionUUID) {
        ResponseEntity<String> result = checkIfAttractionExistsAndIsNotAlreadyLeft(ticketUUID, attractionUUID);
        if (result != null) {
            return result;
        }
        attractionLeaveUseCase.leaveAttraction(new LeaveAttractionCommand(new Ticket.TicketUUID(ticketUUID), new Attraction.AttractionUUID(attractionUUID)));
        return ResponseEntity.ok("The stand with UUID " + attractionUUID + " has been successfully removed.");

    }

    public ResponseEntity<String> checkIfAttractionExistsAndIsNotAlreadyLeft(UUID uuid, UUID attractionUUId) {
        Attraction attraction = attractionEnitityQuery.getAttraction(new Attraction.AttractionUUID(attractionUUId));
        // Check if there are any recent activities for the specified stand UUID
        ActivityWindow activityWindow = attraction.getActivityWindow();

        List<AttractionQueueActivity> activitiesForAttraction = activityWindow.getActivitiesForUUID(attractionUUId);


        if (activitiesForAttraction.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The attraction with UUID " + uuid + " does not have this person.");
        }

        AttractionQueueActivity mostRecentActivity = activitiesForAttraction.get(activitiesForAttraction.size() - 1);
        if (mostRecentActivity.action() == QueueAction.Leave) {
            // The person has already left the attraction
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Person with Ticket UUID " + uuid + " has already left attraction " + attractionUUId + ".");
        }

        return null;
    }


    @GetMapping("/size/attraction/{uuid}")
    public int getQueueSize(@PathVariable UUID uuid) {
        return attractionQueueSizeQuery.getQueueSizeForAttraction(new Attraction.AttractionUUID(uuid));
    }


    @GetMapping("/attraction/{uuid}")
    public AttractionDto getAttraction(@PathVariable UUID uuid) {

        Attraction attraction = attractionEnitityQuery.getAttraction(new Attraction.AttractionUUID(uuid));

        return new AttractionDto(
                attraction.getName(),
                attraction.getVisitorCount(),
                attraction.getAttractionUUID(),
                attraction.getText(),
                attraction.getCategory(),
                attraction.getTag(),
                attraction.getAgeGroup(),
                attraction.getStatus(),
                attraction.getImage(),
                attraction.getMiniumHeight()
        );
    }


    @GetMapping("/attractions/")
    public List<AttractionDto> getAllAttraction() {
        List<Attraction> attractions = attractionEnitityQuery.getAllAttractions();
        List<AttractionDto> attractionDtos = new ArrayList<>();

        for (Attraction attraction : attractions) {
            AttractionDto dto = new AttractionDto(
                    attraction.getName(),
                    attraction.getVisitorCount(),
                    attraction.getAttractionUUID(),
                    attraction.getText(),
                    attraction.getCategory(),
                    attraction.getTag(),
                    attraction.getAgeGroup(),
                    attraction.getStatus(),
                    attraction.getImage(),
                    attraction.getMiniumHeight()
            );
            attractionDtos.add(dto);
        }

        return attractionDtos;
    }


    @GetMapping("/attraction/activities")
    public List<AttractionQueueActivity> getAllAttractionActivities() {
        List<AttractionQueueActivity> activities = attractionActivityQuery.getActivities();
        activities.sort((a, b) -> a.pit().compareTo(b.pit()));
        return activities;
    }


    @PostMapping("/attraction/{attractionUUID}/amount/{amount}")
    public void attractionAdmitManyPeople(@PathVariable UUID attractionUUID, @PathVariable int amount) {
        for (int i = 0; i < amount; i++) {
            attractionEnteredUseCase.enterAttraction(new EnterAttractionCommand(new Ticket.TicketUUID(UUID.randomUUID()), new Attraction.AttractionUUID(attractionUUID)));
        }
    }
}

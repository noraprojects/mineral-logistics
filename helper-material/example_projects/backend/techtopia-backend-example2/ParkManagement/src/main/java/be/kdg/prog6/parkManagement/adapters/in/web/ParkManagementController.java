package be.kdg.prog6.parkManagement.adapters.in.web;

import be.kdg.prog6.parkManagement.domain.*;
import be.kdg.prog6.parkManagement.ports.in.ParkVisitorsCountQuery;
import be.kdg.prog6.parkManagement.ports.in.RemoveRefreshmentStandCommand;
import be.kdg.prog6.parkManagement.ports.in.RemoveRefreshmentStandUseCase;
import be.kdg.prog6.parkManagement.ports.in.VisitorActivityQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@RestController
public class ParkManagementController {


    private final ParkVisitorsCountQuery parkVisitorsCountQuery;
    private final RemoveRefreshmentStandUseCase removeRSUseCase;
    private final VisitorActivityQuery visitorActivityQuery;

    public ParkManagementController(ParkVisitorsCountQuery parkVisitorsCountQuery, RemoveRefreshmentStandUseCase removeRSUseCase, VisitorActivityQuery visitorActivityQuery) {
        this.parkVisitorsCountQuery = parkVisitorsCountQuery;
        this.removeRSUseCase = removeRSUseCase;
        this.visitorActivityQuery = visitorActivityQuery;
    }

    @GetMapping("/park/visitorCount")
    public int getVisitorsCount() {
        return parkVisitorsCountQuery.getVisitorsCount();
    }

    @DeleteMapping("/park/stand/{uuid}")
    public ResponseEntity<String> deleteStand(@PathVariable UUID uuid) {
        ResponseEntity<String> result = checkIfStandExistsAndIsClosed(uuid);
        if (result != null) {
            return result;
        }
        removeRSUseCase.removeRs(new RemoveRefreshmentStandCommand(uuid));
        return ResponseEntity.ok("The stand with UUID " + uuid + " has been successfully removed.");

    }


    @GetMapping("/park/activities/")
    public List<VisitorActivity> getactivies() {
        List<VisitorActivity> activities = visitorActivityQuery.getActivities();
        activities.sort(Comparator.comparing(VisitorActivity::pit));
        return activities;
    }

    public ResponseEntity<String> checkIfStandExistsAndIsClosed(UUID uuid) {
        // Retrieve the activity window from the park
        StandActivityWindow activityWindow = Park.getInstance().getStandActivityWindow();
        // Check if there are any recent activities for the specified stand UUID
        List<RefreshmentsActivity> activitiesForStand = activityWindow.getActivitiesForUUID(uuid);
        // Check if the stand with the specified UUID exists
        if (activitiesForStand.isEmpty()) {
            // Return an error response indicating that the stand doesn't exist
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The stand with UUID " + uuid + " does not exist.");
        }
        // Check if the most recent activity is a "close" action
        RefreshmentsActivity mostRecentActivity = activitiesForStand.get(activitiesForStand.size() - 1);
        if (mostRecentActivity.action() == RefreshmentStandAction.CLOSE) {
            // Return an error response indicating that the stand is already closed
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("The stand with UUID " + uuid + " is already closed and cannot be removed.");
        }
        return null;
    }

}

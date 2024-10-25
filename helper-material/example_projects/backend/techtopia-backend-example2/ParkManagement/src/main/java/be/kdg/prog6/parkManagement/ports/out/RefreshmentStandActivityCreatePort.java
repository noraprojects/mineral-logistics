package be.kdg.prog6.parkManagement.ports.out;

import be.kdg.prog6.parkManagement.domain.RefreshmentsActivity;

import java.util.UUID;

public interface RefreshmentStandActivityCreatePort {
    void createRSActivity(UUID standUUID, RefreshmentsActivity refreshmentstandActivity);

}

package be.kdg.prog6.parkManagement.ports.out;

import java.util.UUID;

public interface RefreshmentStandCreatePort {
    void refreshmentStandCreated(UUID standUUID);

    void refreshmentStandDeleted(UUID standUUID);
}

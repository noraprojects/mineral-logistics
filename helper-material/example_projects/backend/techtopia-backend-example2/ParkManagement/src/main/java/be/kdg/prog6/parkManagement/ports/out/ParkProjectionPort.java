package be.kdg.prog6.parkManagement.ports.out;

import java.util.UUID;

public interface ParkProjectionPort {

    void increment(UUID code);
    void decrement(UUID code);
}

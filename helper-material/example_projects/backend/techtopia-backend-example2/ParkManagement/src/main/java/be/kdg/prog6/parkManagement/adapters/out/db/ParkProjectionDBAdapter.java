package be.kdg.prog6.parkManagement.adapters.out.db;

import be.kdg.prog6.parkManagement.domain.Park;
import be.kdg.prog6.parkManagement.ports.out.ParkProjectionPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ParkProjectionDBAdapter implements ParkProjectionPort {


    @Override
    public void increment(UUID code) {
        Park.getInstance().incrementDailyVisitorCount(code);
    }

    @Override
    public void decrement(UUID code) {
        Park.getInstance().decreaseDailyVisitorCount(code);
    }
}

package be.kdg.prog6.family.ports.in;

import org.springframework.transaction.annotation.Transactional;

public interface BuyingCandyUseCase {
    @Transactional
    void buyingCandy(BuyCandyAmountCommand buyCandyAmountCommand);
}

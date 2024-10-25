package be.kdg.prog6.ticketingTest.unit;

import be.kdg.prog6.ticketing.domain.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PricingStrategyTest {

    @Test
    public void testCompositePricingStrategy() {
        // Arrange
        Pricing expectedFinalPrice = new Pricing(new BigDecimal("70.00"));
        Guest gilles = Guest.createGuest("Gilles");

        gilles.setLoyaltyMember(true);

        PricingStrategy dayPassStrategy = new DayPassPricingStrategy();
        PricingStrategy loyaltyStrategy = new LoyaltyPricingStrategy();

        CompositePricingStrategy compositeStrategy = new CompositePricingStrategy();
        compositeStrategy.addPricingStrategy(dayPassStrategy);
        compositeStrategy.addPricingStrategy(loyaltyStrategy);

        TicketFactory ticketFactory = new TicketFactory();
        Ticket ticket = ticketFactory.createDayPass(gilles.getSso(), LocalDate.now(), compositeStrategy);

        // Act
        Pricing finalPrice = ticket.getPricing();

        // Assert
        assertEquals(expectedFinalPrice.getAmount(), finalPrice.getAmount(), "Price should be reduced by composite strategy");
    }
}

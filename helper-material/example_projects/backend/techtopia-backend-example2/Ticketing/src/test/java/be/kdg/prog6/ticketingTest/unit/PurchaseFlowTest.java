package be.kdg.prog6.ticketingTest.unit;


import be.kdg.prog6.ticketing.domain.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PurchaseFlowTest {

    @Test
    void givenGuest_whenPurchasingTicket_thenTicketIsCreated() {
        // Arrange
        String guestName = "gilles";
        LocalDate date = LocalDate.now();
        LocalDateTime date2 = LocalDateTime.now();


        // Act
        Guest gilles = Guest.createGuest(guestName);
        DayPassPricingStrategy pricingStrategy = new DayPassPricingStrategy();
        TicketFactory ticketFactory = new TicketFactory();

        Ticket purchasedTicket = ticketFactory.createDayPass(gilles.getSso(), date, pricingStrategy);

        // Assert
        assertNotNull(gilles, "Guest should be created");
        assertEquals(guestName, gilles.getName(), "Guest name should be set correctly");
        assertNotNull(gilles.getSso(), "Guest should have an SSO");

        assertNotNull(purchasedTicket, "Ticket should be created");
        assertEquals(date2.truncatedTo(ChronoUnit.SECONDS), purchasedTicket.getIssueDate().truncatedTo(ChronoUnit.SECONDS), "Purchased ticket should be for the correct date");
        assertEquals(new Pricing(new BigDecimal("90.00")).getAmount(), purchasedTicket.getPricing().getAmount(), "Purchased ticket should have correct pricing");
    }
}

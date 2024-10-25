package be.kdg.prog6.ticketing.domain;

import java.math.BigDecimal;

public class LoyaltyPricingStrategy implements PricingStrategy {
    public Pricing calculatePrice(Ticket ticket) {
        return new Pricing(ticket.getPricing().getAmount().subtract(new BigDecimal("20.00")));
    }
}

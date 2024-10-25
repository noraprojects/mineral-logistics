package be.kdg.prog6.ticketing.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DayPassPricingStrategy implements PricingStrategy {

    @Override
    public Pricing calculatePrice(Ticket ticket) {
        return new Pricing(ticket.getPricing().getAmount().subtract(new BigDecimal("10.00")));
    }
}

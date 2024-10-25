package be.kdg.prog6.ticketing.domain;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

public class WeekPassPricingStrategy implements PricingStrategy {


    public Pricing calculatePrice(WeekPassTicket ticket) {
        long daysBetween = ChronoUnit.DAYS.between(ticket.getStartDate(), ticket.getEndDate());
        BigDecimal dailyPriceAdjustment = new BigDecimal("40.00");
        BigDecimal priceAdjustment = dailyPriceAdjustment.multiply(BigDecimal.valueOf(daysBetween));
        return new Pricing(ticket.getPricing().getAmount().add(priceAdjustment));
    }


    @Override
    public Pricing calculatePrice(Ticket ticket) {
        return ticket.getPricing();
    }
}


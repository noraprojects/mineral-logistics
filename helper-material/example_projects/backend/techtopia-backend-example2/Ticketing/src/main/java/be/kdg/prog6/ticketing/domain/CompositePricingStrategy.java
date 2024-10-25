package be.kdg.prog6.ticketing.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CompositePricingStrategy implements PricingStrategy {

    private final List<PricingStrategy> pricingStrategies;

    public CompositePricingStrategy() {
        this.pricingStrategies = new ArrayList<>();
    }

    public CompositePricingStrategy(List<PricingStrategy> pricingStrategies) {
        this.pricingStrategies = pricingStrategies;
    }

    public void addPricingStrategy(PricingStrategy strategy) {
        pricingStrategies.add(strategy);
    }

    @Override
    public Pricing calculatePrice(Ticket ticket) {
        BigDecimal finalPriceAmount = ticket.getPricing().getAmount();
        BigDecimal cumulativeAdjustment = BigDecimal.ZERO;

        for (PricingStrategy strategy : pricingStrategies) {
            if (strategy instanceof WeekPassPricingStrategy && ticket instanceof WeekPassTicket) {
                Pricing priceAfterStrategy = ((WeekPassPricingStrategy) strategy).calculatePrice((WeekPassTicket) ticket);
                cumulativeAdjustment = cumulativeAdjustment.add(finalPriceAmount.subtract(priceAfterStrategy.getAmount()));
            } else {
                Pricing priceAfterStrategy = strategy.calculatePrice(ticket);
                cumulativeAdjustment = cumulativeAdjustment.add(finalPriceAmount.subtract(priceAfterStrategy.getAmount()));
            }
        }
        finalPriceAmount = finalPriceAmount.subtract(cumulativeAdjustment);
        return new Pricing(finalPriceAmount);
    }


}

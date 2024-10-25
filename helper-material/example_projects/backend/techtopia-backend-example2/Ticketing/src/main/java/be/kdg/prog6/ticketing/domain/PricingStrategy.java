package be.kdg.prog6.ticketing.domain;

public interface PricingStrategy {
    Pricing calculatePrice(Ticket ticket);
}


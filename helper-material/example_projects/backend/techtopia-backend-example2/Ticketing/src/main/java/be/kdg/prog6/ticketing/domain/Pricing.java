package be.kdg.prog6.ticketing.domain;

import java.math.BigDecimal;
import java.util.Currency;

public class Pricing {
    private final BigDecimal amount;
    private final Currency currency;
    private BigDecimal tax;
    private BigDecimal discount;

    public Pricing(BigDecimal amount) {
        this.amount = amount;
        this.currency = Currency.getInstance("USD");
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getFinalAmount() {
        return amount.add(tax).subtract(discount);
    }
}

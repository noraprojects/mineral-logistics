package be.kdg.prog6.ticketing.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Component
public class TicketFactory {

    private static final BigDecimal BASE_DAY_PASS_PRICE = new BigDecimal("100.00");
    private static final BigDecimal BASE_WEEK_PASS_PRICE = new BigDecimal("100.00");

    private static UUID generateTicketId() {
        return java.util.UUID.randomUUID();
    }

    public DayPassTicket createDayPass(Guest.GuestUUID guest, LocalDate visitDate, PricingStrategy pricingStrategy) {
        validateDayPassParameters(visitDate);

        UUID ticketId = generateTicketId();
        ActivityWindow activityWindow = new ActivityWindow();

        DayPassTicket ticket = new DayPassTicket(
                new Ticket.TicketUUID(ticketId),
                guest,
                visitDate,
                new Pricing(BASE_DAY_PASS_PRICE),
                PassType.DAY_PASS,
                activityWindow

        );

        Pricing price = pricingStrategy.calculatePrice(ticket);
        ticket.setPricing(price);

        return ticket;
    }

    public WeekPassTicket createWeekPass(Guest.GuestUUID guest, LocalDate startDate, LocalDate endDate, PricingStrategy pricingStrategy) {
        validateWeekPassParameters(startDate, endDate);

        UUID ticketId = generateTicketId();
        ActivityWindow activityWindow = new ActivityWindow();

        WeekPassTicket ticket = new WeekPassTicket(
                new Ticket.TicketUUID(ticketId),
                guest,
                new Pricing(BASE_WEEK_PASS_PRICE),
                PassType.DAY_PASS,
                activityWindow,
                startDate,
                endDate
        );


        Pricing price = pricingStrategy.calculatePrice(ticket);
        ticket.setPricing(price);

        return ticket;
    }

    private void validateDayPassParameters(LocalDate visitDate) {
        LocalDate currentDate = LocalDate.now();

        if (visitDate.isBefore(currentDate)) {
            throw new IllegalArgumentException("Visit date cannot be before the current date.");
        }
    }

    private void validateWeekPassParameters(LocalDate startDate, LocalDate endDate) {
        LocalDate currentDate = LocalDate.now();

        if (startDate.isBefore(currentDate)) {
            throw new IllegalArgumentException("Start date cannot be before the current date.");
        }

        if (endDate.isBefore(currentDate)) {
            throw new IllegalArgumentException("End date cannot be before the current date.");
        }
    }


}

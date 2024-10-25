package be.kdg.prog6.ticketing.adapters.in.web;

import be.kdg.prog6.ticketing.adapters.out.db.TicketingJpaEntity;
import be.kdg.prog6.ticketing.domain.*;
import be.kdg.prog6.ticketing.ports.in.GetTicketCommand;
import be.kdg.prog6.ticketing.ports.in.GetTicketUseCase;
import be.kdg.prog6.ticketing.ports.in.PurchaseTicketCommand;
import be.kdg.prog6.ticketing.ports.in.PurschaseTicketUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@RestController
public class TicketingController {

    private final PurschaseTicketUseCase ticketUseCase;
    private final GetTicketUseCase getTicketUseCase;

    public TicketingController(PurschaseTicketUseCase ticketUseCase, GetTicketUseCase getTicketUseCase) {
        this.ticketUseCase = ticketUseCase;
        this.getTicketUseCase = getTicketUseCase;
    }
    
    @GetMapping("/ticket/user/{username}")
    public List<TicketingJpaEntity> getTickets(@PathVariable String username) {
        return getTicketUseCase.getTicket(new GetTicketCommand(username));
    }


    @GetMapping("/ticket/price/dayPass")
    public String getTicketPriceDayPass(Principal principal) {
        Guest guest;

        CompositePricingStrategy compositeStrategy = new CompositePricingStrategy();


        if (principal != null) {
            guest = Guest.createGuest(principal.getName());
            guest.setLoyaltyMember(true);
            PricingStrategy loyaltyStrategy = new LoyaltyPricingStrategy();
            compositeStrategy.addPricingStrategy(loyaltyStrategy);
        } else {
            guest = Guest.createGuest("Anonymous");
        }

        PricingStrategy dayPassStrategy = new DayPassPricingStrategy();
        compositeStrategy.addPricingStrategy(dayPassStrategy);

        TicketFactory ticketFactory = new TicketFactory();
        Ticket ticket = ticketFactory.createDayPass(guest.getSso(), LocalDate.now(), compositeStrategy);
        Pricing finalPrice = ticket.getPricing();
        return finalPrice.getAmount().toString();
    }

    @GetMapping("/ticket/price/weekPass/startdate/{startDate}/endDate/{endDate}")
    public String getTicketPriceWeekPass(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate, Principal principal) {
        Guest guest;
        CompositePricingStrategy compositeStrategy = new CompositePricingStrategy();

        if (principal != null) {
            guest = Guest.createGuest(principal.getName());
            guest.setLoyaltyMember(true);
            PricingStrategy loyaltyStrategy = new LoyaltyPricingStrategy();
            compositeStrategy.addPricingStrategy(loyaltyStrategy);
        } else {
            guest = Guest.createGuest("Anonymous");
        }

        PricingStrategy weekPassPricingStrategy = new WeekPassPricingStrategy();
        compositeStrategy.addPricingStrategy(weekPassPricingStrategy);
        TicketFactory ticketFactory = new TicketFactory();
        WeekPassTicket ticket = ticketFactory.createWeekPass(guest.getSso(), startDate, endDate, compositeStrategy);
        Pricing finalPrice = ticket.getPricing();
        return finalPrice.getAmount().toString();
    }

    @PostMapping("/dayPass/{amount}/person/{username}/date/{date}")
    public void purchaseWeekTicket(@PathVariable BigDecimal amount, @PathVariable String username, @PathVariable LocalDate date) {
        ticketUseCase.purchaseDayTicket(new PurchaseTicketCommand(amount, username, date, date));
    }

    @PostMapping("/weekPass/{amount}/person/{username}/startDate/{startDate}/endDate/{endDate}")
    public void purchaseDayTicket(@PathVariable BigDecimal amount, @PathVariable String username, @PathVariable LocalDate startDate, @PathVariable LocalDate endDate) {
        ticketUseCase.purchaseWeekTicket(new PurchaseTicketCommand(amount, username, startDate, endDate));
    }


}

package be.kdg.prog6.ticketing.ports.in;

public interface PurschaseTicketUseCase {
    void purchaseWeekTicket(PurchaseTicketCommand purschaseTicketCommand);

    void purchaseDayTicket(PurchaseTicketCommand purschaseTicketCommand);

}

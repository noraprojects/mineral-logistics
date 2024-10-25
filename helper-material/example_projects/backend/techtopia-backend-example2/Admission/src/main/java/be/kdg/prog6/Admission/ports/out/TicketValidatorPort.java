package be.kdg.prog6.Admission.ports.out;

import be.kdg.prog6.Admission.events.TicketingReceivedTickedEvent;

import java.util.Optional;
import java.util.UUID;

public interface TicketValidatorPort {

    boolean validateTicket(String code);


}

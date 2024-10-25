package be.kdg.prog6.admissionTest.core;

import be.kdg.prog6.Admission.core.DefaultTicketValidator;
import be.kdg.prog6.Admission.ports.in.ValidateTicketCommand;
import be.kdg.prog6.Admission.ports.out.TicketValidatorPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Create a stub implementation of the TicketValidatorPort for testing.
class TicketValidatorPortStub implements TicketValidatorPort {
    @Override
    public boolean validateTicket(String code) {
        if (code.equals("valid_ticket_code")) {
            return true;
        } else {
            return false;
        }
    }
}

public class StubDefailtTicketValidatorTest {

    private DefaultTicketValidator ticketValidator;

    @BeforeEach
    void setUp() {
        // Create the stubbed TicketValidatorPort
        TicketValidatorPort ticketValidatorPort = new TicketValidatorPortStub();
        ticketValidator = new DefaultTicketValidator(ticketValidatorPort);
    }

    @Test
    void validateTicketValidTicketReturnsTrue() {
        // Test the validateTicket method with a valid ticket code.
        boolean result = ticketValidator.validateTicket(new ValidateTicketCommand("valid_ticket_code"));
        // Assert
        assert(result);
    }

    @Test
    void validateTicketInvalidTicketReturnsFalse() {
        // Test the validateTicket method with an invalid ticket code.
        boolean result = ticketValidator.validateTicket(new ValidateTicketCommand("invalid_ticket_code"));
        // Assert
        assert(!result);
    }
}

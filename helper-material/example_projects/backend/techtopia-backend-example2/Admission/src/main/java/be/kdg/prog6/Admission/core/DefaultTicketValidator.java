package be.kdg.prog6.Admission.core;
import be.kdg.prog6.Admission.ports.in.ValdidateUserUseCase;
import be.kdg.prog6.Admission.ports.in.ValidateTicketCommand;
import be.kdg.prog6.Admission.ports.out.TicketValidatorPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DefaultTicketValidator implements ValdidateUserUseCase {
    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultTicketValidator.class);

    private final TicketValidatorPort ticketValidatorPort;


    public DefaultTicketValidator(TicketValidatorPort ticketValidatorPort) {
        this.ticketValidatorPort = ticketValidatorPort;
    }

    @Override
    public boolean validateTicket(ValidateTicketCommand validateTicketCommand) {
        LOGGER.info("VALIDATING TICKET.....BEEP BOOP");
        return ticketValidatorPort.validateTicket(validateTicketCommand.code());
    }
}

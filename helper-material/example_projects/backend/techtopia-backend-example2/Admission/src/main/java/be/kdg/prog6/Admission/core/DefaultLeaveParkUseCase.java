package be.kdg.prog6.Admission.core;


import be.kdg.prog6.Admission.ports.in.LeaveParkCommand;
import be.kdg.prog6.Admission.ports.in.LeaveParkUseCase;
import be.kdg.prog6.Admission.ports.out.LeaveParkPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DefaultLeaveParkUseCase implements LeaveParkUseCase {
    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultLeaveParkUseCase.class);

    private final LeaveParkPort leaveParkPort;

    public DefaultLeaveParkUseCase(LeaveParkPort leaveParkPort) {
        this.leaveParkPort = leaveParkPort;
    }


    @Override
    public void leavePark(LeaveParkCommand validateTicketCommand) {
        leaveParkPort.leavePark(validateTicketCommand.code());
    }
}

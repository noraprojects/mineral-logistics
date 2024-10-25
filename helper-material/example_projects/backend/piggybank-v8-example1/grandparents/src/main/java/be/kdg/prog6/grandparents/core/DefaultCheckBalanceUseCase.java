package be.kdg.prog6.grandparents.core;

import be.kdg.prog6.grandparents.domain.Appointment;
import be.kdg.prog6.grandparents.domain.PiggyBank;
import be.kdg.prog6.grandparents.domain.PiggyBankAction;
import be.kdg.prog6.grandparents.domain.PiggyBankActivityEvent;
import be.kdg.prog6.grandparents.events.PiggyBankActivityCreatedEvent;
import be.kdg.prog6.grandparents.ports.in.CheckBalanceUseCase;
import be.kdg.prog6.grandparents.ports.in.PiggyBankBalanceProjector;
import be.kdg.prog6.grandparents.ports.out.AppointmentCreatePort;
import be.kdg.prog6.grandparents.ports.out.PiggyBankActivityPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DefaultCheckBalanceUseCase implements CheckBalanceUseCase {

    private final AppointmentCreatePort appointmentCreatePort;
    private final PiggyBankBalanceProjector piggyBankBalanceProjector;
    private final PiggyBankActivityPort piggyBankActivityPort;

    public DefaultCheckBalanceUseCase(AppointmentCreatePort appointmentCreatePort, PiggyBankBalanceProjector piggyBankBalanceProjector, PiggyBankActivityPort piggyBankActivityPort) {
        this.appointmentCreatePort = appointmentCreatePort;
        this.piggyBankBalanceProjector = piggyBankBalanceProjector;
        this.piggyBankActivityPort = piggyBankActivityPort;
    }


    @Override
    @Transactional
    public void checkBalance(PiggyBankActivityCreatedEvent event) {
        piggyBankActivityPort.saveState(new PiggyBankActivityEvent(event.amount(), PiggyBankAction.valueOf(event.action()), event.pit()));

        //CAN BE DONE ASYNC!!!!!
        Optional<PiggyBank> piggyBank = piggyBankBalanceProjector.project(event);
        if (piggyBank.isEmpty()) {
            System.out.println(">>>>>> Not a piggybank I know!");
            return;
        }
        if (piggyBank.get().isItTimeToGoShopping()) {
            System.out.println(">>>>>> Balance exceeds 50! - make Appointment!");
            Appointment appointment = Appointment.forToday(piggyBank.get().getPersonUUID());
            appointmentCreatePort.appointmentStateChanged(appointment);
        } else {
            System.out.println(">>>>>> Balance does not exceed 50! - do not make Appointment!");
        }
    }
}

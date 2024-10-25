package be.kdg.prog6.family.core;

import be.kdg.prog6.common.facades.CreateAppointmentCommand;
import be.kdg.prog6.family.domain.*;
import be.kdg.prog6.family.ports.in.AppointmentForShoppingUseCase;
import be.kdg.prog6.family.ports.out.AgendaActivityBookedPort;
import be.kdg.prog6.family.ports.out.PiggyBankActivityCreatePort;
import be.kdg.prog6.family.ports.out.PiggyBankLoadPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultAppointmentForShoppingUseCase implements AppointmentForShoppingUseCase {

    private final PiggyBankLoadPort piggyBankLoadPort;
    private final List<PiggyBankActivityCreatePort> piggyBankActivityCreatePorts;
    private final List<AgendaActivityBookedPort> agendaActivityBookedPort;

    public DefaultAppointmentForShoppingUseCase(PiggyBankLoadPort piggyBankLoadPort, List<PiggyBankActivityCreatePort> piggyBankActivityCreatePorts, List<AgendaActivityBookedPort> agendaActivityBookedPort) {
        this.piggyBankLoadPort = piggyBankLoadPort;
        this.piggyBankActivityCreatePorts = piggyBankActivityCreatePorts;
        this.agendaActivityBookedPort = agendaActivityBookedPort;
    }


    @Override
    @Transactional
    public boolean bookAnAppointmentForDate(CreateAppointmentCommand createAppointmentCommand) {
        Optional<AgendaActivity> activity = Agenda.instance().bookAppointment(createAppointmentCommand.personUUID(), createAppointmentCommand.date());

        //if we booked something then an event has to be thrown and the activity needs to be saved...
        //just call all appropriate ports
        //in this instance  see @link DefaultReceivingMoneyUseCase to see it all implemented.

        //for now just going to broadcast an event, that is of interest myself
        // -> it creates a create moment to create a snapshot of our actvitywindows.


        if (activity.isPresent()) {
            //we can also withdraw money from the piggybank in order to reserve it for the shopping date!
            reserveMoneyForShopping(createAppointmentCommand.personUUID());

            //if we booked something then an event has to be thrown and the activity needs to be saved...s
            //just call all appropriate ports
            //in this instance  see @link DefaultReceivingMoneyUseCase to see it all implemented.

            //for now just going to broadcast an event, that is of interest myself
            // -> it creates a create moment to create a snapshot of our actvitywindows.
            agendaActivityBookedPort.stream().forEach(a -> a.agendaActivityBooked(activity.get()));
        }


        return activity.isPresent();

    }

    private void reserveMoneyForShopping(UUID personUUID) {

        Optional<PiggyBank> optionalPiggyBank = piggyBankLoadPort.loadPiggyBankForOwner(new Person.PersonUUID(personUUID));
        //use all money in the piggybank of reserve a shopping budget
        if (optionalPiggyBank.isPresent()) {
            Optional<PiggyBankActivity> activity = optionalPiggyBank.get().takeMoney(optionalPiggyBank.get().getBalance());
            activity.ifPresent(piggyBankActivity -> piggyBankActivityCreatePorts.forEach(port -> port.createPiggyBankActivity(optionalPiggyBank.get().getPiggyBankUUID(), piggyBankActivity)));
        }
    }


}

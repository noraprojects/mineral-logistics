package be.kdg.prog6.family.ports.in;

import be.kdg.prog6.common.facades.CreateAppointmentCommand;

public interface AppointmentForShoppingUseCase {


    boolean bookAnAppointmentForDate(CreateAppointmentCommand giveMoneyCommand);


}

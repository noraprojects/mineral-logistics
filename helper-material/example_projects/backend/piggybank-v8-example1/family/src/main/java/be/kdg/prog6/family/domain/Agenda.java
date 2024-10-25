package be.kdg.prog6.family.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;


public class Agenda {


    private final static Logger LOGGER = LoggerFactory.getLogger(Agenda.class);


    private static final Agenda INSTANCE = new Agenda();


    private AgendaWindow agendaWindow = new AgendaWindow();


    public static final Agenda instance() {
        return INSTANCE;
    }

    private Agenda() {
    }


    private boolean isFree(UUID personUUID, LocalDate date) {
        //let's say it is always free
        //couple of possibilities here
        //get a snapshot of this date
        //get a window of activities on this period (month for instance)
        //inject a repository that queries the date itself.
        return true;
    }

    public Optional<AgendaActivity> bookAppointment(UUID personUUID, LocalDate date) {
        if (isFree(personUUID, date)) {
            LOGGER.info("Appointment booked!");
            AgendaActivity activity = new AgendaActivity(personUUID, date, AgendaAction.BOOK, "Going Shopping with grandma!");
            agendaWindow.addActivity(activity);
            return Optional.of(activity);
        }

        return Optional.empty();

    }


}

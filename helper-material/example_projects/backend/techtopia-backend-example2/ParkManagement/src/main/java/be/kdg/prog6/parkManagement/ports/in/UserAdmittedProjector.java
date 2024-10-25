package be.kdg.prog6.parkManagement.ports.in;

import be.kdg.prog6.parkManagement.events.UserAdmittedActivityEvent;

public interface UserAdmittedProjector {

    void admitUser(UserAdmittedActivityEvent event);

}

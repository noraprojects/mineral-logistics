package be.kdg.prog6.parkManagement.ports.in;

import be.kdg.prog6.parkManagement.events.UserDismissedActivityEvent;

public interface UserDissmissProjector {

    void dismissUser(UserDismissedActivityEvent event);

}

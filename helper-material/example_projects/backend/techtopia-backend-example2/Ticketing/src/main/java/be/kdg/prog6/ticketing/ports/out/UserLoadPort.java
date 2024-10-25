package be.kdg.prog6.ticketing.ports.out;

import be.kdg.prog6.ticketing.domain.Guest;

import java.util.Optional;

public interface UserLoadPort {

    Optional<Guest> loadGuestByID(String username);
}

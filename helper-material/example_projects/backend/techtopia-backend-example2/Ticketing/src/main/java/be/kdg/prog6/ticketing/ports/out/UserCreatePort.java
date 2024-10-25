package be.kdg.prog6.ticketing.ports.out;

import be.kdg.prog6.ticketing.domain.Guest;

public interface UserCreatePort {
    Guest createUser(String username);
}

package be.kdg.prog6.ticketing.adapters.out.db;

import be.kdg.prog6.ticketing.domain.Guest;
import be.kdg.prog6.ticketing.ports.out.UserCreatePort;
import be.kdg.prog6.ticketing.ports.out.UserLoadPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserDBAdapter implements UserLoadPort , UserCreatePort {
    private final UserRepository userRepository;

    public UserDBAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Optional<Guest> loadGuestByID(String username) {
        Optional<UserJpaEntity> userJpaEntity = userRepository.findByUserName(username);
        Guest guest = null;
        if (userJpaEntity.isPresent()) {
            guest = new Guest(userJpaEntity.get().getUserName(), new Guest.GuestUUID(userJpaEntity.get().getUserID()));
        }
        return Optional.ofNullable(guest);
    }

    @Override
    public Guest createUser(String username) {
        UserJpaEntity userJpaEntity = userRepository.save(new UserJpaEntity(username,UUID.randomUUID()));
        return new Guest(userJpaEntity.getUserName(),new Guest.GuestUUID(userJpaEntity.getUserID()));
    }
}

package be.kdg.prog6.grandparents.ports.in;

import be.kdg.prog6.grandparents.domain.PiggyBank;
import be.kdg.prog6.grandparents.events.PiggyBankActivityCreatedEvent;

import java.util.Optional;

public interface PiggyBankBalanceProjector {

    Optional<PiggyBank> project(PiggyBankActivityCreatedEvent event);
}

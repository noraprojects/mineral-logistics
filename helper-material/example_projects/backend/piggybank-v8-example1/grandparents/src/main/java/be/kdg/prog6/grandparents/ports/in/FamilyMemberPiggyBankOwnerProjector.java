package be.kdg.prog6.grandparents.ports.in;

import be.kdg.prog6.grandparents.domain.PiggyBank;
import be.kdg.prog6.grandparents.events.FamilyReceivedPiggyBankEvent;

import java.util.Optional;

public interface FamilyMemberPiggyBankOwnerProjector {

    Optional<PiggyBank> project(FamilyReceivedPiggyBankEvent event);
}

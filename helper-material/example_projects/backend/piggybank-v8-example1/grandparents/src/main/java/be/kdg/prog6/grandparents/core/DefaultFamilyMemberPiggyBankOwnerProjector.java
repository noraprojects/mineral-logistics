package be.kdg.prog6.grandparents.core;

import be.kdg.prog6.grandparents.domain.GrandKids;
import be.kdg.prog6.grandparents.domain.PiggyBank;
import be.kdg.prog6.grandparents.events.FamilyReceivedPiggyBankEvent;
import be.kdg.prog6.grandparents.ports.in.FamilyMemberPiggyBankOwnerProjector;
import be.kdg.prog6.grandparents.ports.out.PiggyBankProjectionPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DefaultFamilyMemberPiggyBankOwnerProjector implements FamilyMemberPiggyBankOwnerProjector {

    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultFamilyMemberPiggyBankOwnerProjector.class);


    private final PiggyBankProjectionPort piggyBankProjectionPort;

    public DefaultFamilyMemberPiggyBankOwnerProjector(PiggyBankProjectionPort piggyBankProjectionPort) {
        this.piggyBankProjectionPort = piggyBankProjectionPort;
    }

    @Override
    @Transactional
    public Optional<PiggyBank> project(FamilyReceivedPiggyBankEvent event) {
        if (GrandKids.isGrandKid(event.name())) {
            LOGGER.info(">>>>>>  Bobby received a piggyBank!!!!!!!!");
            piggyBankProjectionPort.savePiggybank(new PiggyBank(new PiggyBank.PiggyBankUUID(event.piggyBank()), new PiggyBank.PersonUUID(event.owner())));
        } else {
            LOGGER.info(">>>>>> NOT MY GRANDCHILD? NOT INTERESTED!!!!!!!!");
        }
        return Optional.empty();
    }
}

package be.kdg.prog6.parkManagement.core;

import be.kdg.prog6.parkManagement.adapters.out.db.RefreshmentStandJpaEntity;
import be.kdg.prog6.parkManagement.adapters.out.db.RefreshmentStandJpaRepository;
import be.kdg.prog6.parkManagement.domain.Park;
import be.kdg.prog6.parkManagement.domain.RefreshmentsActivity;
import be.kdg.prog6.parkManagement.ports.in.RefreshmentStandUseCase;
import be.kdg.prog6.parkManagement.ports.in.RefreshmentStandCommand;
import be.kdg.prog6.parkManagement.ports.out.RefreshmentStandActivityCreatePort;
import be.kdg.prog6.parkManagement.ports.out.RefreshmentStandCreatePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultRefreshmentStandUseCase implements RefreshmentStandUseCase {

    private final RefreshmentStandCreatePort refreshmentStandCreatePort;

    private final List<RefreshmentStandActivityCreatePort> refreshmentStandCreatePortList;

    private final RefreshmentStandJpaRepository refresmentStandJpaRepository;

    public DefaultRefreshmentStandUseCase(RefreshmentStandCreatePort refreshmentStandCreatePort, List<RefreshmentStandActivityCreatePort> refreshmentStandCreatePortList, RefreshmentStandJpaRepository refresmentStandJpaRepository) {
        this.refreshmentStandCreatePort = refreshmentStandCreatePort;
        this.refreshmentStandCreatePortList = refreshmentStandCreatePortList;
        this.refresmentStandJpaRepository = refresmentStandJpaRepository;
    }


    @Override
    public void manageRefreshmentStand(RefreshmentStandCommand command) {

        Park park = Park.getInstance();


        for (int i = 0; i < command.amount(); i++) {
            RefreshmentStandJpaEntity refreshmentStand = refresmentStandJpaRepository.findAll().get(i);
            refreshmentStandCreatePort.refreshmentStandCreated(refreshmentStand.getUuid());
            RefreshmentsActivity activity = park.addStand(refreshmentStand.getUuid());
            refreshmentStandCreatePortList.forEach(port -> port.createRSActivity(refreshmentStand.getUuid(), activity));
        }


    }
}

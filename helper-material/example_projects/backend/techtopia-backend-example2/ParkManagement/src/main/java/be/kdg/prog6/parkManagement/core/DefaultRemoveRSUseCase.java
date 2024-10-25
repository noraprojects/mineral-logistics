package be.kdg.prog6.parkManagement.core;

import be.kdg.prog6.parkManagement.domain.Park;
import be.kdg.prog6.parkManagement.domain.RefreshmentsActivity;
import be.kdg.prog6.parkManagement.ports.in.RemoveRefreshmentStandCommand;
import be.kdg.prog6.parkManagement.ports.in.RemoveRefreshmentStandUseCase;
import be.kdg.prog6.parkManagement.ports.out.RefreshmentStandActivityCreatePort;
import be.kdg.prog6.parkManagement.ports.out.RefreshmentStandCreatePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultRemoveRSUseCase implements RemoveRefreshmentStandUseCase {

    private final RefreshmentStandCreatePort refreshmentStandCreatePort;

    private final List<RefreshmentStandActivityCreatePort> refreshmentStandCreatePortList;

    public DefaultRemoveRSUseCase(RefreshmentStandCreatePort refreshmentStandCreatePort, List<RefreshmentStandActivityCreatePort> refreshmentStandCreatePortList) {
        this.refreshmentStandCreatePort = refreshmentStandCreatePort;
        this.refreshmentStandCreatePortList = refreshmentStandCreatePortList;
    }


    @Override
    public void removeRs(RemoveRefreshmentStandCommand removeRSCommand) {
        Park park = Park.getInstance();
        refreshmentStandCreatePort.refreshmentStandDeleted(removeRSCommand.RSUUID());
        RefreshmentsActivity activity = park.removeStand(removeRSCommand.RSUUID());
        refreshmentStandCreatePortList.forEach(port -> port.createRSActivity(removeRSCommand.RSUUID(), activity));
    }

}

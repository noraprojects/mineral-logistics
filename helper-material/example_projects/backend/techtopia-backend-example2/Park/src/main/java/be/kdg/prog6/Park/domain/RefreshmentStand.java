package be.kdg.prog6.Park.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class RefreshmentStand {

    private UUID standID;
    private String name;

    public RefreshmentStand(UUID uuid) {
        this.standID = uuid;
    }

    public RefreshmentStand() {
    }
}

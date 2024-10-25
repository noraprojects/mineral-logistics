package be.kdg.prog6.parkManagement.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;
import java.util.UUID;

@Setter
@Getter
public class RefreshmentStand {

    private UUID standID;
    private String name;

    public RefreshmentStand() {
        this.standID = UUID.randomUUID();
    }
}

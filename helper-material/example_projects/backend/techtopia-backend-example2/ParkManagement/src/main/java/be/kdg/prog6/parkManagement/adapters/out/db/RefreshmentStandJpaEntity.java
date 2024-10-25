package be.kdg.prog6.parkManagement.adapters.out.db;


import be.kdg.prog6.parkManagement.domain.Attraction;
import be.kdg.prog6.parkManagement.domain.AttractionStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(schema = "parkmanagement", name = "refreshmentsStand")
@Getter
@Setter
public class RefreshmentStandJpaEntity {
    @Id
    private UUID uuid;


    @Column
    private AttractionStatus attractionStatus;

    public RefreshmentStandJpaEntity(UUID uuid) {
        this.uuid = uuid;
        this.attractionStatus = AttractionStatus.closed;
    }

    public RefreshmentStandJpaEntity() {
    }
}




package be.kdg.prog6.parkManagement.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Setter
@Getter
public class Attraction {
    private AttractionUUID attractionUUID;
    private AttractionType type;
    private AttractionStatus status;
    private Throughput throughput;
    private int maximumOccupancy;
    private int currentThroughput;

    public Attraction(AttractionUUID attractionUUID) {
        this.attractionUUID = attractionUUID;
    }

    public record AttractionUUID(UUID uuid) {
    }

}


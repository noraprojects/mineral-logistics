package be.kdg.prog6.landside.domain;

import java.util.UUID;

public class LicensePlate {
    private final LicensePlateUUID sso;
    private final PlateNumber plateNumber;

    public LicensePlate(LicensePlateUUID sso, PlateNumber plateNumber) {
        this.sso = sso;
        this.plateNumber = plateNumber;

    }


    public record LicensePlateUUID(UUID uuid) {}

    public record PlateNumber(String plateNumber) {}

    public LicensePlateUUID getSso() {
        return sso;
    }

    public PlateNumber getLicensePlate() {
        return plateNumber;
    }

}

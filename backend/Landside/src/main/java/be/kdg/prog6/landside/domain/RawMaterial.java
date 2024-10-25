package be.kdg.prog6.landside.domain;

public enum RawMaterial {
    GYPSUM("Gypsum", 1.0, 13.0),
    IRON_ORE("Iron Ore", 5.0, 110.0),
    CEMENT("Cement", 3.0, 95.0),
    PETCOKE("Petcoke", 10.0, 210.0),
    SLAG("Slag", 7.0, 160.0);

    private final String name;
    private final double storagePricePerTonPerDay;
    private final double pricePerTon;

    RawMaterial(String name, double storagePricePerTonPerDay, double pricePerTon) {
        this.name = name;
        this.storagePricePerTonPerDay = storagePricePerTonPerDay;
        this.pricePerTon = pricePerTon;
    }

    public String getName() {
        return name;
    }

    public double getStoragePricePerTonPerDay() {
        return storagePricePerTonPerDay;
    }

    public double getPricePerTon() {
        return pricePerTon;
    }
}

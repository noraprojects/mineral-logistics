package be.kdg.prog6.landside.domain;

import java.time.LocalDateTime;

public class PayloadDeliveryTicket {
    final RawMaterial rawMaterial;
    final Warehouse warehouse;
    final Seller seller;
    final LocalDateTime loadMaterialDateTime;
    final int dockNumber;

    public PayloadDeliveryTicket(RawMaterial rawMaterial, Warehouse warehouse, Seller seller, LocalDateTime loadMaterialDateTime, int dockNumber) {
        this.rawMaterial = rawMaterial;
        this.warehouse = warehouse;
        this.seller = seller;
        this.loadMaterialDateTime = loadMaterialDateTime;
        this.dockNumber = dockNumber;
    }
}

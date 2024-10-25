package be.kdg.prog6.landside.domain;

public class Warehouse {

    private String warehouseNumber;
    RawMaterial rawMaterial;
    Seller seller;
    final double WAREHOUSE_MAXIMUM_CAPACITY_TONS = 500000;
    double currentStockInTons; //we need to know this info from warehouse context

    public RawMaterial getRawMaterial() {
        return rawMaterial;
    }

    public Seller getSeller() {
        return seller;
    }

    public double getWAREHOUSE_MAXIMUM_CAPACITY_TONS() {
        return WAREHOUSE_MAXIMUM_CAPACITY_TONS;
    }

    public double getCurrentStockInTons() {
        return currentStockInTons;
    }

    public void setRawMaterial(RawMaterial rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void setCurrentStockInTons(double currentStockInTons) {
        this.currentStockInTons = currentStockInTons;
    }

    public boolean isWarehouseFull(){ // message from warehouse context
        return currentStockInTons/WAREHOUSE_MAXIMUM_CAPACITY_TONS > 0.8;
    }

    //from warehouse context
    public void setWarehouseNumber(String warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public String getWarehouseNumber() {
        return warehouseNumber;
    }

}

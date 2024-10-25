package be.kdg.prog6.landside.domain;

import java.util.List;
import java.util.UUID;

public class Seller {
    private String companyName;
    private SellerUUID sso;

    private List<Warehouse> warehouseNumbers; //from warehouse context

    public Seller(String companyName, SellerUUID sso) {
        this.companyName = companyName;
        this.sso = sso;
    }

    public String getCompanyName() {
        return companyName;
    }

    public List<Warehouse> getWarehouseNumbers() {
        return warehouseNumbers;
    }

    public void setWarehouseNumbers(List<Warehouse> warehouseNumbers) {
        this.warehouseNumbers = warehouseNumbers;
    }

    public void addWarehouse(Warehouse warehouse) {
        if (warehouse != null) {
            warehouseNumbers.add(warehouse);
        }
    }

    public void removeWarehouse(Warehouse warehouse) {
        if (warehouse != null) {
            warehouseNumbers.remove(warehouse);
        }
    }


    //WAREHOUSE CONTEXT
    //TO DO: METHOD TO CHECK MAX 5 WAREHOUSES, EACH HAS DIFFERENT MATERIAL, FOR EACH SELLER
    //TO DO: ADD WAREHOUSE
    //TO DO: REMOVE A WAREHOUSE

    public record SellerUUID(UUID uuid) {}
}

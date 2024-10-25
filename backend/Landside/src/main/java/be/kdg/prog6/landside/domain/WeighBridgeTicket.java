package be.kdg.prog6.landside.domain;

import java.time.LocalDateTime;

public class WeighBridgeTicket {
    private final double arrivalWeight;
    private final  LocalDateTime arrivalDateTime;
    private final Truck truck;
    private double departureWeight;
    private double netWeight;
    private LocalDateTime departureDateTime;


    public WeighBridgeTicket(Truck truck, double arrivalWeight, LocalDateTime arrivalDateTime) {
        this.arrivalWeight = arrivalWeight;
        this.arrivalDateTime = arrivalDateTime;
        this.truck = truck;
    }

    public double getNetWeight() {
        return netWeight = arrivalWeight - departureWeight;
    }

    public void setDepartureWeight(double departureWeight) {
        this.departureWeight = departureWeight;
    }

    public void setDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }




}

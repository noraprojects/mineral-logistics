package be.kdg.prog6.landside.domain;

public class Truck {
 final LicensePlate licensePlate;
 int arrivalBridgeNumber;
 int departureBridgeNumber;
 Warehouse warehouse;
 WeighBridgeTicket weighBridgeTicket;
 PayloadDeliveryTicket payloadDeliveryTicket;

 public Truck(LicensePlate licensePlate) {
  this.licensePlate = licensePlate;
 }

 public void setArrivalBridgeNumber(int arrivalBridgeNumber) {
  this.arrivalBridgeNumber = arrivalBridgeNumber;
 }

 public void setDepartureBridgeNumber(int departureBridgeNumber) {
  this.departureBridgeNumber = departureBridgeNumber;
 }

 public void setWarehouse(Warehouse warehouse) {
  this.warehouse = warehouse;
 }

 public void setWeighBridgeTicket(WeighBridgeTicket weighBridgeTicket) {
  this.weighBridgeTicket = weighBridgeTicket;
 }

 public void setPayloadDeliveryTicket(PayloadDeliveryTicket payloadDeliveryTicket) {
  this.payloadDeliveryTicket = payloadDeliveryTicket;
 }

 public LicensePlate getLicensePlate() {
  return licensePlate;
 }

 public int getArrivalBridgeNumber() {
  return arrivalBridgeNumber;
 }

 public int getDepartureBridgeNumber() {
  return departureBridgeNumber;
 }

 public Warehouse getWarehouse() {
  return warehouse;
 }

 public WeighBridgeTicket getWeighBridgeTicket() {
  return weighBridgeTicket;
 }

 public PayloadDeliveryTicket getPayloadDeliveryTicket() {
  return payloadDeliveryTicket;
 }
}

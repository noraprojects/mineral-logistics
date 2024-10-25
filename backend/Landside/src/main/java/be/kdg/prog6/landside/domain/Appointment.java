package be.kdg.prog6.landside.domain;

import java.time.LocalDateTime;

public class Appointment {
    private final Truck truck;
    private final LocalDateTime appointmentStartTime;
    private final LocalDateTime appointmentEndTime;
    private final RawMaterial materialType;
    private  AppointmentAction action;
    private final Seller seller;

    public Appointment(Truck truck, Seller seller, RawMaterial materialType, LocalDateTime appointmentStartTime, LocalDateTime appointmentEndTime) {
        this.truck = truck;
        this.seller = seller;
        this.materialType = materialType;
        this.appointmentStartTime = appointmentStartTime;
        this.appointmentEndTime = appointmentEndTime;
        this.action = AppointmentAction.APPROVED; // Initial state
    }


    public LocalDateTime getAppointmentStartTime() {
        return appointmentStartTime;
    }

    public RawMaterial getMaterialType() {
        return materialType;
    }

    public AppointmentAction getAction() {
        return action;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setAction(AppointmentAction action) {
        this.action = action;
    }

    public Truck getTruck() {
        return truck;
    }


    public LocalDateTime getAppointmentEndTime() {
        return appointmentEndTime;
    }

    public void completeAppointment() {
        this.action = AppointmentAction.COMPLETED;
    }

    public void cancelAppointment() {
        this.action = AppointmentAction.CANCELED;
    }
}



package be.kdg.prog6.ticketing.domain;

import java.util.UUID;

public class Guest {

    private String name;
    private GuestUUID sso;
    private boolean loyaltyMember;

    public Guest(String name, GuestUUID sso) {
        this.name = name;
        this.sso = sso;
    }

    public static Guest createGuest(String name) {
        return new Guest(name, new GuestUUID(UUID.randomUUID()));
    }

    public boolean isLoyaltyMember() {
        return loyaltyMember;
    }

    public void setLoyaltyMember(boolean loyaltyMember) {
        this.loyaltyMember = loyaltyMember;
    }

    public String getName() {
        return name;
    }

    public GuestUUID getSso() {
        return sso;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "name='" + name + '\'' +
                ", sso=" + sso +
                '}';
    }

    public record GuestUUID(UUID uuid) {
    }

}

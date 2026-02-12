package Parkinglot;

import VehicleFactory.Vehicle;

import java.util.Date;

public class Ticket {
    private final Vehicle vehicle;
    private final ParkingSpot spot;
    private final String id;
    private final long entryTimestamp;
    private long exitTimestamp;

    public Ticket(Vehicle vehicle, ParkingSpot spot, String id) {
        this.vehicle = vehicle;
        this.spot = spot;
        this.id = id;
        this.entryTimestamp = System.currentTimeMillis();
    }

    public void setExitTimestamp(int minute_offset) {
        long offsetMillis = minute_offset * 60L * 1000L;
        this.exitTimestamp = System.currentTimeMillis() + offsetMillis;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public ParkingSpot getSpot() {
        return this.spot;
    }

    public String getId() {
        return this.id;
    }

    public long getEntryTimestamp() {
        return this.entryTimestamp;
    }

    public long getExitTimestamp() {
        return this.exitTimestamp;
    }
}

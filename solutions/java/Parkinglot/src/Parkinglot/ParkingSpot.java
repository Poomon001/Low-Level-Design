package Parkinglot;

import FeeSystem.FeeRate;
import FeeSystem.VehicleBasedHourlyRate;
import VehicleFactory.Vehicle;
import VehicleFactory.VehicleType;

public class ParkingSpot {
    private final int spotId;
    private final VehicleType spotType;
    private Vehicle vehicle;
    private Boolean isOccupied;

    public ParkingSpot(VehicleType spotType, int spotId) {
        this.spotType = spotType;
        this.spotId = spotId;
        this.isOccupied = false;
    }

    public synchronized boolean park(Vehicle vehicle) {
        if (this.isOccupied || this.spotType != vehicle.getType()) {
            return false;
        }
        this.vehicle = vehicle;
        this.isOccupied = true;
        return true;
    }

    public synchronized void unpark() {
        this.vehicle = null;
        this.isOccupied = false;
    }

    public synchronized boolean isOccupied() {
        return this.isOccupied;
    }

    public int getSpotId() {
        return this.spotId;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public VehicleType getSpotType() {
        return this.spotType;
    }
}

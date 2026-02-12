package Parkinglot;

import VehicleFactory.Vehicle;

import java.util.List;
import java.util.Optional;

public class ParkingLevel {
    private final List<ParkingSpot> parkingSpots; // a list of empty spots
    private final int level;

    public ParkingLevel(int level, List<ParkingSpot> parkingSpots) {
        this.level = level;
        this.parkingSpots = parkingSpots;
    }

    public int getLevel() {
        return this.level;
    }

    protected List<ParkingSpot> getParkingSpots() {
        return List.copyOf(parkingSpots);
    }

    public Optional<ParkingSpot> getParkingSpot(Vehicle vehicle) {
        for(ParkingSpot spot:this.parkingSpots) {
            if(spot.park(vehicle)) {
                return Optional.of(spot); // a reference
            }
        }
        return Optional.empty();
    }

    public int getTotalEmptySpots(Vehicle vehicle) {
        return (int) this.parkingSpots.stream()
                .filter(spot -> !spot.isOccupied())
                .filter(spot -> spot.getSpotType() == vehicle.getType())
                .count();
    }

    public void freeParkingSpot(ParkingSpot spot) {
        spot.unpark();
    }
}

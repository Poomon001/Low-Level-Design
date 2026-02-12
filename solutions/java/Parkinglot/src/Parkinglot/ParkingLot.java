package Parkinglot;

import FeeSystem.FeeRate;
import FeeSystem.FreeRate;
import FeeSystem.VehicleBasedHourlyRate;
import VehicleFactory.Vehicle;
import Exception.ParkingLotFullException;
import Exception.UnparkVehicleException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private static final ParkingLot instance = new ParkingLot();
    private final List<ParkingLevel> parkingLevels;
    private final ConcurrentHashMap<String, Ticket> activeTickets;
    private FeeRate feeRate;

    private ParkingLot() {
        this.activeTickets = new ConcurrentHashMap<>();
        this.parkingLevels = new ArrayList<>();
        this.feeRate = new VehicleBasedHourlyRate();
    }

    public void addParkingLevel(ParkingLevel parkingLevel) {
        this.parkingLevels.add(parkingLevel);
    }

    public List<ParkingLevel> getParkingLevels() {
        return List.copyOf(parkingLevels);
    }

    public synchronized Ticket park(Vehicle vehicle) throws ParkingLotFullException {
        for (ParkingLevel level : this.parkingLevels) {
            Optional<ParkingSpot> parkingSpot = level.getParkingSpot(vehicle);
            if(parkingSpot.isPresent()) {
                String id = UUID.randomUUID().toString();
                Ticket ticket = new Ticket(vehicle, parkingSpot.get(), id);
                activeTickets.put(id, ticket);
                return ticket;
            }
        }
         throw new ParkingLotFullException("The parking lot is full.");
    }

    public synchronized double unpark(String ticketId, int offset) throws UnparkVehicleException {
        Ticket ticket = activeTickets.remove(ticketId);
        if(ticket == null) {
            throw new UnparkVehicleException("Invalid ticket id");
        }
        ticket.setExitTimestamp(offset);
        ticket.getSpot().unpark();
        return feeRate.calculationFee(ticket);
    }

    public void setFeeRate(FeeRate freeRate) {
        this.feeRate = feeRate;
    }

    public String getFeeRate() {
        return this.feeRate.getFeeRate();
    }

    public static void showParkingMap(List<ParkingLevel> parkingLevels) {
        for(ParkingLevel parkingLevel : parkingLevels) {
            System.out.println("=== Level: " + parkingLevel.getLevel() + " ===");
            for(ParkingSpot spot : parkingLevel.getParkingSpots()) {
                if(spot.isOccupied()) {
                    System.out.printf("[%s]", spot.getVehicle().getLicensePlate());
                } else {
                    System.out.print("[] ");
                }
            }
            System.out.println("");
        }
    }

    public static ParkingLot getInstance() {
        return instance;
    }
}

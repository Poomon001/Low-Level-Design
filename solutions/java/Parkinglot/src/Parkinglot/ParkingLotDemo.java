package Parkinglot;

import FeeSystem.FeeRate;
import FeeSystem.VehicleBasedHourlyRate;
import VehicleFactory.*;
import Exception.ParkingLotFullException;
import Exception.UnparkVehicleException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ParkingLotDemo {
    public static void main(String[] args) {
        ParkingLot parkingLot = ParkingLot.getInstance();
        List<ParkingSpot> parkingSpotsLevel1 = List.of(
            new ParkingSpot(VehicleType.Motorcycle, 1),
            new ParkingSpot(VehicleType.Motorcycle, 2),
            new ParkingSpot(VehicleType.Motorcycle, 3),
            new ParkingSpot(VehicleType.Truck, 100),
            new ParkingSpot(VehicleType.Truck, 101)
        );
        List<ParkingSpot> parkingSpotsLevel2 = List.of(
                new ParkingSpot(VehicleType.Car, 90),
                new ParkingSpot(VehicleType.Car, 91),
                new ParkingSpot(VehicleType.Car, 92),
                new ParkingSpot(VehicleType.Motorcycle,4),
                new ParkingSpot(VehicleType.Motorcycle, 5)
        );
        List<ParkingSpot> parkingSpotsLevel3 = List.of(
                new ParkingSpot(VehicleType.Car, 93),
                new ParkingSpot(VehicleType.Car, 94),
                new ParkingSpot(VehicleType.Car, 95)
        );
        ParkingLevel level1 = new ParkingLevel(1, parkingSpotsLevel1);
        ParkingLevel level2 = new ParkingLevel(2, parkingSpotsLevel2);
        ParkingLevel level3 = new ParkingLevel(3, parkingSpotsLevel3);

        // add parking levels
        parkingLot.addParkingLevel(level1);
        parkingLot.addParkingLevel(level2);
        parkingLot.addParkingLevel(level3);

        // create vehicles
        Vehicle car1 = new Car("1A3BC4");
        Vehicle car2 = new Car("3X5BA2");
        Vehicle bike = new Motorcycle("X123");
        Vehicle truck = new Truck("BCX436");

        ParkingLot.showParkingMap(parkingLot.getParkingLevels());
        List<Ticket> tickets = new ArrayList<>();

        // park
        try {
            Ticket car1Ticket = parkingLot.park(car1);
            printParkingMap(parkingLot);

            Ticket car2Ticket = parkingLot.park(car2);
            printParkingMap(parkingLot);

            Ticket bikeTicket = parkingLot.park(bike);
            printParkingMap(parkingLot);

            Ticket truckTicket = parkingLot.park(truck);
            printParkingMap(parkingLot);

            tickets.add(car1Ticket);
            tickets.add(car2Ticket);
            tickets.add(bikeTicket);
            tickets.add(truckTicket);
        } catch (ParkingLotFullException e) {
            System.out.println("The parking lot is full: " + e);
        } catch (Exception e) {
            System.out.println("Failed to inset ticket.");
        }

        System.out.println("Done");
        // unpark
        try {
            int hour_offset = 0;
            for(Ticket ticket:tickets) {
                double fee = parkingLot.unpark(ticket.getId(), hour_offset * 60);
                System.out.printf("A %s [%s] paid %f USD%n",
                        ticket.getVehicle().getType(),
                        ticket.getVehicle().getLicensePlate(),
                        fee
                );

                printParkingMap(parkingLot);
                hour_offset += 1;
            }
        } catch (UnparkVehicleException e) {
            System.out.println("Failed to unpark: " + e);
        } catch (Exception e) {
            System.out.println("Failed to display.");
        }
    }

    public static void printParkingMap(ParkingLot parkingLot) {
        System.out.println("");
        ParkingLot.showParkingMap(parkingLot.getParkingLevels());
        System.out.println("");
    }
}

package FeeSystem;

import Parkinglot.Ticket;
import VehicleFactory.VehicleType;

import java.util.Map;

public class VehicleBasedHourlyRate implements FeeRate {
    private final Map<VehicleType, Integer> vehicleRate = Map.of(
            VehicleType.Truck, 10,
            VehicleType.Car, 5,
            VehicleType.Motorcycle, 2
    );

    @Override
    public double calculationFee(Ticket ticket) {
        long millis = ticket.getExitTimestamp() - ticket.getEntryTimestamp();
        int hour = (int) Math.ceil(millis / (1000 * 60 * 60));
        int rate = vehicleRate.getOrDefault(
                ticket.getVehicle().getType(), 0);
        return hour * rate;
    }

    @Override
    public String getFeeRate() {
        return "Vehicle Based Hourly Rate";
    }
}


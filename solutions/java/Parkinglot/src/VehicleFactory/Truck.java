package VehicleFactory;

public class Truck extends Vehicle {
    public Truck(String licensePlate) {
        super(VehicleType.Truck, licensePlate);
    }

    @Override
    public String getLicensePlate() {
        return "T:" + this.licensePlate;
    }
}

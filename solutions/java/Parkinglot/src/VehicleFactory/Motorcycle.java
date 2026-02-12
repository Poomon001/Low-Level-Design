package VehicleFactory;

public class Motorcycle extends Vehicle {
    public Motorcycle(String licensePlate) {
        super(VehicleType.Motorcycle, licensePlate);
    }

    @Override
    public String getLicensePlate() {
        return "M:" + this.licensePlate;
    }
}

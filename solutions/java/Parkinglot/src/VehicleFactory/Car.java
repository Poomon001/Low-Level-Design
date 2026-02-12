package VehicleFactory;

public class Car extends Vehicle {
    public Car(String licensePlate) {
        super(VehicleType.Car, licensePlate);
    }

    @Override
    public String getLicensePlate() {
        return "C:" + this.licensePlate;
    }
}

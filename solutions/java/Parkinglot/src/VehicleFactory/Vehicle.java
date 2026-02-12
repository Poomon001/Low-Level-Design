package VehicleFactory;

public abstract class Vehicle {
    VehicleType type;
    String licensePlate;

    public Vehicle(VehicleType type, String licensePlate) {
        this.type = type;
        this.licensePlate = licensePlate;
    }

    public VehicleType getType() {
        return this.type;
    }

    public String getLicensePlate(){
        return this.licensePlate;
    }
}

// enum is type safety (Coin != Note),
// fixed set of available values,
// and access control (constructor of enum cannot instantiated explicitly)
public enum Coin {
    // public static final COIN PENNY = new COIN(0.01);
    PENNY(0.01),
    NICKEL(0.05),
    DIME(0.10),
    QUARTER(0.25),
    LOONIE(1.00),
    TOONIE(2.00);

    private final double value;

    Coin(double value) {
        this.value = value;
    }

    public double getValue(){
        return this.value;
    }
}

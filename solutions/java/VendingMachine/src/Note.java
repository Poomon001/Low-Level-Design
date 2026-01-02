// enum is type safety (Coin != Note),
// fixed set of available values,
// and access control (constructor of enum cannot instantiated explicitly)
public enum Note{
    // public static final Note FIVE = new Note(5.00);
    FIVE(5.00),
    TEN(10.00),
    TWENTY(20.00),
    FIFTY(50.00),
    HUNDRED(100.00);

    private final double value;

    Note(double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }
}

public class PaymentProcessor {
    public double process(double price, double paid) {
        if(paid < price) {
            throw new IllegalArgumentException("insufficient paid amount");
        }
        System.out.println(String.format("Paid: %f", paid));
        return paid - price;
    }
}

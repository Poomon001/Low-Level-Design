package FeeSystem;

import Parkinglot.Ticket;

public class FreeRate implements FeeRate {

    @Override
    public double calculationFee(Ticket ticket) {
        return 0;
    }

    @Override
    public String getFeeRate() {
        return "Free Rate";
    }
}

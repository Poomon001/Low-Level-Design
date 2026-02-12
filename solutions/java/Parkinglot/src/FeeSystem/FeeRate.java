package FeeSystem;

import Parkinglot.Ticket;

public interface FeeRate {
    double calculationFee(Ticket ticket);
    String getFeeRate();
}

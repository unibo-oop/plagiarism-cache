package model.implementations;

import java.io.Serializable;

import model.enumerations.TravelClass;
import model.interfaces.Booking;
import model.interfaces.Flight;
import model.interfaces.Passenger;

/**
 * 
 * Implements a booking.
 */
public class BookingImpl implements Booking, Serializable {

    private static final long serialVersionUID = 7984742602864093732L;

    private final String bookingId;
    private final Flight flight;
    private final TravelClass travelClass;
    private final Passenger passenger;
    private double finalPrice;

    /**
     * Creates a new booking.
     * 
     * @param flgt         the booked flight
     * @param trvlClass    the booked travel class
     * @param pssngr       the passenger who made the reservation
     */
    public BookingImpl(final Flight flgt, final TravelClass trvlClass, final Passenger pssngr) {
        super();
        this.bookingId = IdGeneratorImpl.getIdGenerator().generate();
        this.flight = flgt;
        this.travelClass = trvlClass;
        this.passenger = pssngr;
        this.finalPrice = 0.0;
    }

    @Override
    public String getBookingId() {
        return this.bookingId;
    }

    @Override
    public Flight getFlight() {
        return this.flight;
    }

    @Override
    public TravelClass getTravelClass() {
        return this.travelClass;
    }

    @Override
    public Passenger getPassenger() {
        return this.passenger;
    }

    @Override
    public double getFinalPrice() {
        return this.finalPrice;
    }

    @Override
    public void setFinalPrice(final double price) {
        final double roundedPrice = (double) Math.round(price * 100) / 100;
        this.finalPrice = roundedPrice;
    }

    @Override
    public String toString() {
        return "ID: " + this.bookingId + ", FLIGHT: " + this.flight.getFlightId() + ", TRAVEL CLASS: " + this.travelClass
                + ", PASSENGER: " + this.passenger.getDocumentId() + ", " + this.passenger.getFiscalCode()
                + ", " + this.passenger.getName() + ", " + this.passenger.getSurname() + ", FINAL PRICE: " + this.finalPrice;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.bookingId == null) ? 0 : this.bookingId.hashCode());
        long temp;
        temp = Double.doubleToLongBits(this.finalPrice);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((this.flight == null) ? 0 : this.flight.hashCode());
        result = prime * result + ((this.passenger == null) ? 0 : this.passenger.hashCode());
        result = prime * result + ((this.travelClass == null) ? 0 : this.travelClass.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BookingImpl other = (BookingImpl) obj;
        if (this.bookingId == null) {
            if (other.bookingId != null) {
                return false;
            }
        } else if (!this.bookingId.equals(other.bookingId)) {
            return false;
        }
        return true;
    }

}
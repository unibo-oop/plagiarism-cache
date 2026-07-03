package model.implementations;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import model.interfaces.Destination;
import model.interfaces.Flight;
import model.interfaces.Plane;

/**
 * 
 * Implements a flight.
 */
public class FlightImpl implements Flight, Serializable {

    private static final long serialVersionUID = 6852857130400230110L;

    private final String flightId;
    private final Plane plane;
    private final Destination destination;
    private final Date departureDate;
    private final Date arrivalDate;
    private final String departureTime;
    private final String arrivalTime;
    private final double basicPrice;
    private final List<String> flightAttendantIdentifiersList;
    private final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);

    /**
     * Creates a new flight.
     * 
     * @param pln                      the plane
     * @param dest                     the destination
     * @param depDate                  the date of departure
     * @param arrDate                  the date of arrival
     * @param depTime                  the time of departure
     * @param arrTime                  the time of arrival
     * @param price                    the flight's basic price
     * @param faIdentifiersList        the list of the identifiers of the flight attendants
     */
    public FlightImpl(final Plane pln, final Destination dest, final Date depDate, final Date arrDate, final String depTime,
            final String arrTime, final double price, final List<String> faIdentifiersList) {
        super();
        this.flightId = IdGeneratorImpl.getIdGenerator().generate();
        this.plane = pln;
        this.destination = dest;
        this.departureDate = depDate;
        this.arrivalDate = arrDate;
        this.departureTime = depTime;
        this.arrivalTime = arrTime;
        this.basicPrice = price;
        this.flightAttendantIdentifiersList = faIdentifiersList;
    }

    @Override
    public long getDurationInHours() {
        final long diff = this.arrivalDate.getTime() - this.departureDate.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public String getFlightId() {
        return this.flightId;
    }

    @Override
    public Plane getPlane() {
        return this.plane;
    }

    @Override
    public Destination getDestination() {
        return this.destination;
    }

    @Override
    public Date getDepartureDate() {
        return this.departureDate;
    }

    @Override
    public Date getArrivalDate() {
        return this.arrivalDate;
    }

    @Override
    public String getDepartureTime() {
        return this.departureTime;
    }

    @Override
    public String getArrivalTime() {
        return this.arrivalTime;
    }

    @Override
    public double getBasicPrice() {
        return this.basicPrice;
    }

    @Override
    public List<String> getFlightAttendantIdentifiersList() {
        return this.flightAttendantIdentifiersList;
    }

    @Override
    public String toString() {
        return "ID: " + this.flightId + ", PLANE ID: " + this.plane.getPlaneId() + ", AIRLINE: " + this.plane.getAirlineName()
               + ", ECONOMY CLASS SEATS: " + this.plane.getNEconomyClassAvailableSeats()
               + ", BUSINESS CLASS SEATS: " + this.plane.getNBusinessClassAvailableSeats()
               + ", FIRST CLASS SEATS: " + this.plane.getNFirstClassAvailableSeats()
               + ", DESTINATION: " + this.destination.getCity() + ", " + this.destination.getCountry()
               + ", DEPARTURE DATE: " + this.dateFormat.format(this.departureDate)
               + ", ARRIVAL DATE: " + this.dateFormat.format(this.arrivalDate)
               + ", DEPARTURE TIME: " + this.departureTime + ", ARRIVALTIME: " + this.arrivalTime
               + ", BASIC PRICE: " + this.basicPrice + ", FLIGHT ATTENDANTS: " + this.flightAttendantIdentifiersList;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.arrivalDate == null) ? 0 : this.arrivalDate.hashCode());
        result = prime * result + ((this.arrivalTime == null) ? 0 : this.arrivalTime.hashCode());
        long temp;
        temp = Double.doubleToLongBits(this.basicPrice);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((this.dateFormat == null) ? 0 : this.dateFormat.hashCode());
        result = prime * result + ((this.departureDate == null) ? 0 : this.departureDate.hashCode());
        result = prime * result + ((this.departureTime == null) ? 0 : this.departureTime.hashCode());
        result = prime * result + ((this.destination == null) ? 0 : this.destination.hashCode());
        result = prime * result + ((this.flightAttendantIdentifiersList == null) ? 0 : this.flightAttendantIdentifiersList.hashCode());
        result = prime * result + ((this.flightId == null) ? 0 : this.flightId.hashCode());
        result = prime * result + ((this.plane == null) ? 0 : this.plane.hashCode());
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
        final FlightImpl other = (FlightImpl) obj;
        if (this.flightId == null) {
            if (other.flightId != null) {
                return false;
            }
        } else if (!this.flightId.equals(other.flightId)) {
            return false;
        }
        return true;
    }

}
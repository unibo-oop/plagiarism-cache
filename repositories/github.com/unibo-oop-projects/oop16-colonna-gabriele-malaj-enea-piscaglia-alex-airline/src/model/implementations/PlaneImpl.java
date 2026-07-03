package model.implementations;

import java.io.Serializable;

import model.enumerations.Status;
import model.enumerations.TravelClass;
import model.interfaces.Plane;

/**
 * 
 * Implements a plane.
 */
public class PlaneImpl implements Plane, Serializable {

    private static final long serialVersionUID = 2711856959282265838L;

    private final String planeId;
    private final String airlineName;
    private int nEconomyClassAvailableSeats;
    private final int nEconomyClassTotalSeats;
    private int nBusinessClassAvailableSeats;
    private final int nBusinessClassTotalSeats;
    private int nFirstClassAvailableSeats;
    private final int nFirstClassTotalSeats;
    private Status status;

    /**
     * Creates a new plane.
     * 
     * @param lineName                          the name of the airline to which the plane belongs
     * @param numEconomyClassTotalSeats         the total number of economy class seats
     * @param numBusinessClassTotalSeats        the total number of business class seats
     * @param numFirstClassTotalSeats           the total number of first class seats
     */
    public PlaneImpl(final String lineName, final int numEconomyClassTotalSeats,
                     final int numBusinessClassTotalSeats, final int numFirstClassTotalSeats) {
        super();
        this.planeId = IdGeneratorImpl.getIdGenerator().generate();
        this.airlineName = lineName;
        this.nEconomyClassAvailableSeats = numEconomyClassTotalSeats;
        this.nEconomyClassTotalSeats = numEconomyClassTotalSeats;
        this.nBusinessClassAvailableSeats = numBusinessClassTotalSeats;
        this.nBusinessClassTotalSeats = numBusinessClassTotalSeats;
        this.nFirstClassAvailableSeats = numFirstClassTotalSeats;
        this.nFirstClassTotalSeats = numFirstClassTotalSeats;
        this.status = Status.AT_AIRPORT;
    }

    @Override
    public void bookSeat(final TravelClass tc) {
        switch (tc) {
            case ECONOMY: 
                this.nEconomyClassAvailableSeats--;
                break;
            case BUSINESS: 
                this.nBusinessClassAvailableSeats--;
                break;
            case FIRST: 
                this.nFirstClassAvailableSeats--;
                break;
            default:
        }
    }

    @Override
    public Boolean isClassFull(final TravelClass tc) {
        switch (tc) {
            case ECONOMY: 
                return this.nEconomyClassAvailableSeats == 0;
            case BUSINESS: 
                return this.nBusinessClassAvailableSeats == 0;
            case FIRST: 
                return this.nBusinessClassAvailableSeats == 0;
            default:
                return false;
        }
    }

    @Override
    public boolean isPlaneFull() {
        return this.nEconomyClassAvailableSeats == 0 && this.nBusinessClassAvailableSeats == 0 && this.nFirstClassAvailableSeats == 0;
    }

    @Override
    public void resetSeats() {
        this.nEconomyClassAvailableSeats = this.nEconomyClassTotalSeats;
        this.nBusinessClassAvailableSeats = this.nBusinessClassTotalSeats;
        this.nFirstClassAvailableSeats = this.nFirstClassTotalSeats;
    }

    @Override
    public String getPlaneId() {
        return this.planeId;
    }

    @Override
    public String getAirlineName() {
        return this.airlineName;
    }

    @Override
    public int getNEconomyClassAvailableSeats() {
        return this.nEconomyClassAvailableSeats;
    }

    @Override
    public int getNEconomyClassTotalSeats() {
        return this.nEconomyClassTotalSeats;
    }

    @Override
    public int getNBusinessClassAvailableSeats() {
        return this.nBusinessClassAvailableSeats;
    }

    @Override
    public int getNBusinessClassTotalSeats() {
        return this.nBusinessClassTotalSeats;
    }

    @Override
    public int getNFirstClassAvailableSeats() {
        return this.nFirstClassAvailableSeats;
    }

    @Override
    public int getNFirstClassTotalSeats() {
        return this.nFirstClassTotalSeats;
    }

    @Override
    public Status getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(final Status stat) {
        this.status = stat;
    }

    @Override
    public String toString() {
        return "ID: " + this.planeId + ", AIRLINE: " + this.airlineName + ", ECONOMY CLASS SEATS: " + this.nEconomyClassTotalSeats
                + ", BUSINESS CLASS SEATS: " + this.nBusinessClassTotalSeats + ", FIRST CLASS SEATS: " + this.nFirstClassTotalSeats 
                + ", STATUS: " + this.status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.airlineName == null) ? 0 : this.airlineName.hashCode());
        result = prime * result + this.nBusinessClassAvailableSeats;
        result = prime * result + this.nBusinessClassTotalSeats;
        result = prime * result + this.nEconomyClassAvailableSeats;
        result = prime * result + this.nEconomyClassTotalSeats;
        result = prime * result + this.nFirstClassAvailableSeats;
        result = prime * result + this.nFirstClassTotalSeats;
        result = prime * result + ((this.planeId == null) ? 0 : this.planeId.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
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
        final PlaneImpl other = (PlaneImpl) obj;
        if (this.planeId == null) {
            if (other.planeId != null) {
                return false;
            }
        } else if (!this.planeId.equals(other.planeId)) {
            return false;
        }
        return true;
    }

}
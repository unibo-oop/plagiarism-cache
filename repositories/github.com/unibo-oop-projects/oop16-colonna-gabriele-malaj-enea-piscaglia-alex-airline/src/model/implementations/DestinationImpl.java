package model.implementations;

import java.io.Serializable;

import model.interfaces.Destination;

/**
 * 
 * Implements a destination.
 */
public class DestinationImpl implements Destination, Serializable {

    private static final long serialVersionUID = -6828886092892673343L;

    private final String destinationId;
    private final String country;
    private final String city;
    private final String airportName;

    /**
     * Creates a new destination.
     * 
     * @param destinationIdentifier    the destination identifier
     * @param nation                   the destination country
     * @param town                     the destination city
     * @param airport                  the destination airport's name
     */
    public DestinationImpl(final String destinationIdentifier, final String nation, final String town, final String airport) {
        super();
        this.destinationId = destinationIdentifier;
        this.country = nation;
        this.city = town;
        this.airportName = airport;
    }

    @Override
    public String getDestinationId() {
        return this.destinationId;
    }

    @Override
    public String getCountry() {
        return this.country;
    }

    @Override
    public String getCity() {
        return this.city;
    }

    @Override
    public String getAirportName() {
        return this.airportName;
    }

    @Override
    public String toString() {
        return "ID: " + this.destinationId + ", COUNTRY: " + this.country + ", CITY: " + this.city
                + ", AIRPORT NAME: " + this.airportName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.airportName == null) ? 0 : this.airportName.hashCode());
        result = prime * result + ((this.city == null) ? 0 : this.city.hashCode());
        result = prime * result + ((this.country == null) ? 0 : this.country.hashCode());
        result = prime * result + ((this.destinationId == null) ? 0 : this.destinationId.hashCode());
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
        final DestinationImpl other = (DestinationImpl) obj;
        if (destinationId == null) {
            if (other.destinationId != null) {
                return false;
            }
        } else if (!destinationId.equals(other.destinationId)) {
            return false;
        }
        return true;
    }

}
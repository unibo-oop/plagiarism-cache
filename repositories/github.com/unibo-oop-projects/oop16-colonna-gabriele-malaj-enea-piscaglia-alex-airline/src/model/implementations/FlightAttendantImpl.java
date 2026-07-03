package model.implementations;

import java.io.Serializable;
import java.util.Date;

import model.interfaces.FlightAttendant;

/**
 * 
 * Implements a flight attendant.
 */
public class FlightAttendantImpl extends PersonImpl implements FlightAttendant, Serializable {

    private static final long serialVersionUID = 6312246241671936981L;

    private final String flightAttendantId;

    /**
     * Creates a new flight attendant.
     * 
     * @param name           the flight attendant's name
     * @param surname        the flight attendant's surname
     * @param gndr           the flight attendant's gender 
     * @param dateOfBirth    the flight attendant's date of birth
     * @param fiscalCode     the flight attendant's fiscal code
     * @param phoneNumber    the flight attendant's telephone number
     * @param mail           the flight attendant's e-mail
     */
    public FlightAttendantImpl(final String name, final String surname, final String gndr, final Date dateOfBirth, final String fiscalCode,
            final String phoneNumber, final String mail) {
        super(name, surname, gndr, dateOfBirth, fiscalCode, phoneNumber, mail);
        this.flightAttendantId = IdGeneratorImpl.getIdGenerator().generate();
    }

    @Override
    public String getFlightAttendantId() {
        return this.flightAttendantId;
    }

    @Override
    public String toString() {
        return "ID: " + this.flightAttendantId + super.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((this.flightAttendantId == null) ? 0 : this.flightAttendantId.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FlightAttendantImpl other = (FlightAttendantImpl) obj;
        if (this.flightAttendantId == null) {
            if (other.flightAttendantId != null) {
                return false;
            }
        } else if (!this.flightAttendantId.equals(other.flightAttendantId)) {
            return false;
        }
        return true;
    }

}

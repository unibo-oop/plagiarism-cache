package model.implementations;

import java.io.Serializable;
import java.util.Date;

import model.interfaces.Pilot;

/**
 * 
 * Implements a pilot.
 */
public class PilotImpl extends PersonImpl implements Pilot, Serializable {

    private static final long serialVersionUID = -6898535350385200288L;

    private final String pilotId;
    private final String flightLicenseId;

    /**
     * Creates a new pilot.
     * 
     * @param name                       the pilot's name
     * @param surname                    the pilot's surname
     * @param gender                     the pilot's gender
     * @param birthDate                  the pilot's date of birth
     * @param fiscalCode                 the pilot's fiscal code
     * @param telephoneNumber            the pilot's telephone number
     * @param email                      the pilot's e-mail
     * @param flightLicenseIdentifier    the flight license identifier of the pilot
     */
    public PilotImpl(final String name, final String surname, final String gender, final Date birthDate, final String fiscalCode,
                     final String telephoneNumber, final String email, final String flightLicenseIdentifier) {
        super(name, surname, gender, birthDate, fiscalCode, telephoneNumber, email);
        this.pilotId = IdGeneratorImpl.getIdGenerator().generate();
        this.flightLicenseId = flightLicenseIdentifier;
    }

    @Override
    public String getPilotId() {
        return this.pilotId;
    }

    @Override
    public String getFlightLicenseId() {
        return this.flightLicenseId;
    }

    @Override
    public String toString() {
        return "ID: " + this.pilotId + ", LICENSE ID: " + this.flightLicenseId + super.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((this.flightLicenseId == null) ? 0 : this.flightLicenseId.hashCode());
        result = prime * result + ((this.pilotId == null) ? 0 : this.pilotId.hashCode());
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
        final PilotImpl other = (PilotImpl) obj;
        if (this.pilotId == null) {
            if (other.pilotId != null) {
                return false;
            }
        } else if (!this.pilotId.equals(other.pilotId)) {
            return false;
        }
        return true;
    }

}

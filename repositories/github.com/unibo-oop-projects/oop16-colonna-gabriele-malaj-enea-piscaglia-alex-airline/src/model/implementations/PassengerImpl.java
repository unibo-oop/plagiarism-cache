package model.implementations;

import java.io.Serializable;
import java.util.Date;

import model.interfaces.Passenger;

/**
 * 
 * Implements a passenger.
 */
public class PassengerImpl extends PersonImpl implements Passenger, Serializable {

    private static final long serialVersionUID = 3562253791390872563L;

    private final String documentId;
    private final int age;

    /**
     * Creates a new passenger.
     * 
     * @param documentIdentifier    the document identifier of the passenger
     * @param name                  the passenger's name
     * @param surname               the passenger's surname
     * @param gender                the passenger's gender
     * @param birthDate             the passenger's date of birth
     * @param years                 the passenger's age
     * @param fiscalCode            the passenger's fiscal code
     * @param telephoneNumber       the passenger's telephone number
     * @param email                 the passenger's e-mail
     */
    public PassengerImpl(final String documentIdentifier, final String name, final String surname, final String gender, final Date birthDate,
                         final int years, final String fiscalCode, final String telephoneNumber, final String email) {
        super(name, surname, gender, birthDate, fiscalCode, telephoneNumber, email);
        this.documentId = documentIdentifier;
        this.age = years;
    }

    @Override
    public long getAge() {
       return this.age;
    }

    @Override
    public String getDocumentId() {
        return this.documentId;
    }

    @Override
    public String toString() {
        return "DOCUMENT ID: " + this.documentId + super.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + this.age;
        result = prime * result + ((this.documentId == null) ? 0 : this.documentId.hashCode());
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
        final PassengerImpl other = (PassengerImpl) obj;
        if (this.documentId == null) {
            if (other.documentId != null) {
                return false;
            }
        } else if (!this.documentId.equals(other.documentId)) {
            return false;
        }
        return true;
    }

}

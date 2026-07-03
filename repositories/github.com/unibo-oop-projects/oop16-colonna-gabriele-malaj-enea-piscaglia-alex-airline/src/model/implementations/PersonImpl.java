package model.implementations;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import model.interfaces.Person;

/**
 * 
 * Implements a person.
 */
public abstract class PersonImpl implements Person, Serializable {

    private static final long serialVersionUID = -2734394724956108985L;

    private final String name;
    private final String surname;
    private final String gender;
    private final Date birthDate;
    private final String fiscalCode;
    private final String telephoneNumber;
    private final String email;
    private final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);

    /**
     * Creates a new person.
     * 
     * @param firstName      the person's name
     * @param lastName       the person's surname
     * @param gndr           the person's gender
     * @param dateOfBirth    the person's date of birth
     * @param fc             the person's fiscal code
     * @param phoneNumber    the person's telephone number
     * @param mail           the person's e-mail
     */
    public PersonImpl(final String firstName, final String lastName, final String gndr, final Date dateOfBirth, final String fc,
                      final String phoneNumber, final String mail) {
        super();
        this.name = firstName;
        this.surname = lastName;
        this.gender = gndr;
        this.birthDate = dateOfBirth;
        this.fiscalCode = fc;
        this.telephoneNumber = phoneNumber;
        this.email = mail;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getSurname() {
        return this.surname;
    }

    @Override
    public String getGender() {
        return this.gender;
    }

    @Override
    public Date getBirthDate() {
        return this.birthDate;
    }

    @Override
    public String getFiscalCode() {
        return this.fiscalCode;
    }

    @Override
    public String getTelephoneNumber() {
        return this.telephoneNumber;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String toString() {
        return ", NAME: " + this.name + ", SURNAME: " + this.surname + ", GENDER: " + this.gender
                + ", DATE OF BIRTH: " + this.dateFormat.format(this.birthDate) + ", FISCAL CODE: " + this.fiscalCode
                + ", TELEPHONE NUMBER: " + this.telephoneNumber + ", E-MAIL: " + this.email;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.birthDate == null) ? 0 : this.birthDate.hashCode());
        result = prime * result + ((this.dateFormat == null) ? 0 : this.dateFormat.hashCode());
        result = prime * result + ((this.email == null) ? 0 : this.email.hashCode());
        result = prime * result + ((this.fiscalCode == null) ? 0 : this.fiscalCode.hashCode());
        result = prime * result + ((this.gender == null) ? 0 : this.gender.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.surname == null) ? 0 : this.surname.hashCode());
        result = prime * result + ((this.telephoneNumber == null) ? 0 : this.telephoneNumber.hashCode());
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
        final PersonImpl other = (PersonImpl) obj;
        if (this.fiscalCode == null) {
            if (other.fiscalCode != null) {
                return false;
            }
        } else if (!this.fiscalCode.equals(other.fiscalCode)) {
            return false;
        }
        return true;
    }

}
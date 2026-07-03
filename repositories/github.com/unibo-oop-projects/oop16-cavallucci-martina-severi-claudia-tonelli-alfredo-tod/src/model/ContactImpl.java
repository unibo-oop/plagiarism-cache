package model;

import java.util.Objects;

import org.joda.time.LocalDate;

import com.google.common.base.Optional;

/**
 * This class represents a Contact of the Address Book.
 */

public class ContactImpl implements Contact {

    private static final long serialVersionUID = 7394463841452745083L;

    private final String name;
    private final String surname;
    private final Optional<LocalDate> dateOfBirth;
    private final Optional<String> address;
    private final Optional<String> phoneNumber;
    private final Optional<String> email;

    /**
     * Builds a contact of the address book.
     * 
     * @param name
     *            contact's name
     * @param surname
     *            contact's surname
     * @param dateOfBirth
     *            contact's date of birth
     * @param address
     *            contact's address
     * @param phoneNumber
     *            contact's phone number
     * @param email
     *            contact's email
     */
    ContactImpl(final String name, final String surname, final LocalDate dateOfBirth, final String address,
            final String phoneNumber, final String email) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = Optional.fromNullable(dateOfBirth);
        this.address = Optional.fromNullable(address);
        this.phoneNumber = Optional.fromNullable(phoneNumber);
        this.email = Optional.fromNullable(email);
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
    public String getDateOfBirth() {
        if (this.dateOfBirth.isPresent()) {
            return this.dateOfBirth.get().toString();
        } else {
            return "";
        }
    }

    @Override
    public Optional<LocalDate> getDateOfBirthValue() {
        return this.dateOfBirth;
    }

    @Override
    public String getAddress() {
        return this.address.or("");
    }

    @Override
    public String getPhoneNumber() {
        return this.phoneNumber.or("");
    }

    @Override
    public String getEmail() {
        return this.email.or("");
    }

    @Override
    public String getSurnameName() {
        return this.surname + this.name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
        final ContactImpl other = (ContactImpl) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (surname == null) {
            if (other.surname != null) {
                return false;
            }
        } else if (!surname.equals(other.surname)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ContactImpl [name=" + name + ", surname=" + surname + ", dateOfBirth=" + dateOfBirth + ", address="
                + address + ", phoneNumber=" + phoneNumber + ", email=" + email + "]";
    }

    /**
     * This inner class is the Builder of ContactImpl.
     */
    public static class Builder {

        private String builderName;
        private String builderSurname;
        private LocalDate builderDateOfBirth;
        private String builderAddress;
        private String builderPhoneNumber;
        private String builderEmail;

        /**
         * @param name
         *            name of the contact
         * @return the name of the contact
         */
        public Builder name(final String name) {
            this.builderName = Objects.requireNonNull(name);
            return this;
        }

        /**
         * @param surname
         *            surname of the contact
         * @return the surname of the contact
         */
        public Builder surname(final String surname) {
            this.builderSurname = Objects.requireNonNull(surname);
            return this;
        }

        /**
         * @param dateOfBirth
         *            date of birth of the contact
         * @return the date of birth of the contact
         */
        public Builder dateOfBirth(final LocalDate dateOfBirth) {
            this.builderDateOfBirth = dateOfBirth;
            return this;
        }

        /**
         * @param address
         *            address of the contact
         * @return the address of the contact
         */
        public Builder address(final String address) {
            this.builderAddress = address;
            return this;
        }

        /**
         * @param phoneNumber
         *            phone number of the contact
         * @return the phone number of the contact
         * @throws IllegalArgumentException
         *             if the number contains letters or symbols
         */
        public Builder phoneNumber(final String phoneNumber) throws IllegalArgumentException {
            if (!phoneNumber.chars().allMatch(i -> Character.isDigit(i))) {
                throw new IllegalArgumentException("Invalid phone number");
            }
            this.builderPhoneNumber = phoneNumber;
            return this;
        }

        /**
         * @param email
         *            email of the contact
         * @return the email of the contact
         * @throws IllegalArgumentException
         *             if an email does not contain the @ symbol
         */
        public Builder email(final String email) throws IllegalArgumentException {
            if (!email.contains("@")) {
                throw new IllegalArgumentException("Invalid email");
            }
            this.builderEmail = email;
            return this;
        }

        /**
         * Builds a ContactImpl.
         * 
         * @return a new ContactImpl
         * @throws IllegalArgumentException
         *             if surname and name fields are empty
         */
        public ContactImpl build() throws IllegalArgumentException {
            if (this.builderSurname == null || this.builderName == null) {
                throw new IllegalArgumentException("The surname and name fields are required");
            }
            return new ContactImpl(this.builderName, this.builderSurname, this.builderDateOfBirth, this.builderAddress,
                    this.builderPhoneNumber, this.builderEmail);
        }
    }

}

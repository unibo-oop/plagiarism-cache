package model;

import java.io.Serializable;

import org.joda.time.LocalDate;

import com.google.common.base.Optional;

/**
 * This class represents a Contact of the Address Book.
 */

public interface Contact extends Serializable {
    /**
     * Gets the name of contact.
     * 
     * @return contact's name
     */
    String getName();

    /**
     * Gets the surname of contact.
     * 
     * @return contact's surname
     */
    String getSurname();

    /**
     * Gets a string with the date of birth of contact.
     * 
     * @return contact's date of birth
     */
    String getDateOfBirth();

    /**
     * @return the optional value of the date of birth of contact
     */
    Optional<LocalDate> getDateOfBirthValue();

    /**
     * Gets the address of contact.
     * 
     * @return contact's address
     */
    String getAddress();

    /**
     * Gets the phone number of contact.
     * 
     * @return contact's phone number
     */
    String getPhoneNumber();

    /**
     * Gets the email of contact.
     * 
     * @return contact's email
     */
    String getEmail();

    /**
     * Gets surname and name to sort the collection.
     * 
     * @return contact's surname and name
     */
    String getSurnameName();

}

package model;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

/**
 * An interface modeling a user of the calendar.
 */

public interface User extends Serializable {

    /**
     * @return the name of contact
     */
    String getUsername();

    /**
     * @return the fingerprint of user's password
     */
    String getPassword();

    /**
     * @return the address book of a user
     */
    AddressBook getAddressBook();

    /**
     * @return user's calendar
     */
    Calendar getCalendar();
    /**
     * @return the todo of a user
     */
    ToDo getTodo();

    /**
     * Sets the new password of the user.
     * 
     * @param password
     *            the new user's password
     * @throws NoSuchAlgorithmException
     *             this exception is thrown when a particular cryptographic
     *             algorithm is requested but is not available in the
     *             environment
     */
    void setPassword(String password) throws NoSuchAlgorithmException;
}

package model;

import java.io.Serializable;
import java.util.List;

/**
 * This class represent the User's Address Book.
 */

public interface AddressBook extends Serializable {

    /**
     * Insert a Contact in Address Book.
     * 
     * @param contact
     *            an instance of a Contact of the Address Book
     * @throws IllegalArgumentException
     *             if a Contact is already registered in Address Book
     */
    void addContact(Contact contact) throws IllegalArgumentException;

    /**
     * Delete a Contact from the Address Book.
     * 
     * @param contact
     *            an instance of a Contact of the Address Book
     * @throws IllegalArgumentException
     *             if a Contact is not registered in Address Book
     */
    void removeContact(Contact contact) throws IllegalArgumentException;

    /**
     * Edit a Contact of the Address Book.
     * 
     * @param newContact
     *            an instance of the new contact of the Address Book
     * @param contact
     *            an instance of the original Contact of the Address Book
     * @throws IllegalArgumentException
     *             if the contact cannot be edited
     */
    void editContact(Contact contact, Contact newContact) throws IllegalArgumentException;

    /**
     * Gets a ordered list of all the Contacts of the Address Book.
     * 
     * @return ArrayList of ContactModel
     */
    List<Contact> getAddressBook();

}

package it.unibo.coinquify.telephonebook.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

import it.unibo.coinquify.roommates.model.Person;

/**
 * contact interface.
 */
public interface Contact extends Person, Serializable {

    /**
     * 
     * @return the address of contact.
     */
    String getAddress();

    /**
     * 
     * @return the email address of contact.
     */
    String getEmail();

    /**
     * 
     * @return home phonenumber of contact.
     */
    String getHomePhoneNumber();

    /**
     * 
     * @return work phonenumber of contact.
     */
    String getWorkPhoneNumber();

    /**
     * 
     * @return Optional of birthday date of contact, Optional empty if contact
     *         birthday can't be loaded
     */
    Optional<Date> getBirthday();

    /**
     * 
     * @return if parameters of contact are valid
     */
    boolean isContactValid();

    /**
     * 
     * @param name of contact
     * @param surname of contact
     * @param fiscalCode of contact
     * @param phoneNumber of contact
     * @param birthday of contact
     * @param address of contact
     * @param email of contact
     * @param homeNumber of contact
     * @param workNumber of contact
     */
    @SuppressWarnings("all")
    void editContact(String name, String surname, String fiscalCode, String phoneNumber, Optional<Date> birthday,
            String address, String email, String homeNumber, String workNumber);
}

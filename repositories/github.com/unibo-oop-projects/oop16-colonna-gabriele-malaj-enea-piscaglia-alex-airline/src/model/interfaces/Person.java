package model.interfaces;

import java.util.Date;

/**
 * 
 * Represents a person.
 */
public interface Person {

    /**
     * 
     * @return the person's name
     */
    String getName();

    /**
     * 
     * @return the person's surname
     */
    String getSurname();

    /**
     * 
     * @return the person's gender
     */
    String getGender();

    /**
     * 
     * @return the person's date of birth
     */
    Date getBirthDate();

    /**
     * 
     * @return the person's fiscal code
     */
    String getFiscalCode();

    /**
     * 
     * @return the person's telephone number
     */
    String getTelephoneNumber();

    /**
     * 
     * @return the person's e-mail
     */
    String getEmail();

}
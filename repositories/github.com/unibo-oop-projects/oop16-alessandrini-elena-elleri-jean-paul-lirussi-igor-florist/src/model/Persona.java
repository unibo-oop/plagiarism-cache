package model;

import java.time.LocalDate;

/**
 * interface for the people.
 */
public interface Persona {
    /**
     * @return the name. 
     */
    String getName(); 

    /**
     * @return the surname. 
     */
    String getSurname();

    /**
     * @return the Cf. 
     */
    String getFc(); 


    /**
     * @return the birth date. 
     */
    LocalDate getBirthDate();

    /**
     * @return the age. 
     */
    int getAge(); 



}

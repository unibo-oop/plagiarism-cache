package com.biaren.sportclubmanager.corebundle.model.interfaces;

import java.time.LocalDate;

/**
 * Represent a person.
 * 
 * @author nbrunetti
 */
public interface Person {
    /**
     * Get the person's name.
     * 
     * @return person's name
     */
    String getName();

    /** 
     * Get the person's surname.
     * 
     * @return person's surname
     */
    String getSurname();

    /**
     * Get the person's birth date.
     * 
     * @return person's birth date
     */
    LocalDate getBirthDate();

    /**
     * Get the person's birth place.
     *
     *@return person's birth place
     */
    String getBirthPlace();

    /**
     * Get person's nationality.
     *
     * @return person's nationality
     */
     String getNationality();

     /**
      * Get person's residence address.
      * 
      * @return person's residence address
      */
     String getResidenceAddress();

     /**
      * Get person's residence city.
      *
      * @return person's residence city
      */
     String getResidenceCity();

     /**
      * Get person's district. District referred to the two letters identifier of the residence municipality.
      *
      * @return person's residence district
      */
     String getResidenceDistrict();

     /**
      * Get person's residence nation.
      * @return person's nation
      */
     String getResidenceNation();
     
     /**
      * Get the person's fiscal code.
      * @return person's fiscal code
      */
     String getFiscalCode();
     
     /**
      * Returns a new {@link Builder} with the same property values as this person
      * @return new Builder with the same property values as this person
      */
}
